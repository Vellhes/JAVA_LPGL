package metiers;
import java.io.IOException;

public class Trad {
	//Fonction qui traduit le texte en code morse
	public static String texteToMorse(String chaine) throws IOException {
		String morse="";
		//création de la chaine lettre par lettre
		for(int i = 0; i < chaine.length(); i++) {
			if(chaine.charAt(i)==' ') {
				morse = morse+" ";
			}
			else {
				String lettreCode = Liste.recupMorse(chaine.charAt(i));
				morse = ""+morse+lettreCode;
			}
			morse = morse + " / ";
		}
		return morse;
	}
	//Fonction qui récupère le code morse pour le traduire en texte en utilisant la liste
//	public static String morseToTexte(String chaine) throws IOException {
//        String texte="";
//        int n = chaine.length() - chaine.replace("/", "").length();
//        int index;
//        for(int i = 0; i < n; i++) {
//            if(chaine.substring(0,1)==" ") {
//                texte = texte + " ";
//            }
//            else {
//                index = chaine.indexOf("/");
//                texte = texte+Liste.recupTexte(chaine.substring(0,index));
//                chaine = chaine.substring(index+1);  
//            }
//        }
//        return texte;
//    }
	//Fonction qui créé l'arbre
	public static Arbre creerArbreMorse(String chaine,Liste l) throws IOException {
		String chaineG = chaine+".";
		String chaineD = chaine+"-";
		Arbre a = new Arbre();
		Arbre g = null;
		Arbre d = null;
		Morse morse = new Morse(Liste.recupChar(chaine),chaine);
		//si il existe un code egal a chaineD/chaineG alors création d'un nouveau fils
		if(Liste.rechercheListe(chaineD, l)==true) {
			d=creerArbreMorse(chaineD,l);
		}
		if(Liste.rechercheListe(chaineG, l)==true) {
			g=creerArbreMorse(chaineG,l);
		}
		//Affectation des valeurs a l'arbre
		a.setFeuille(morse);
		a.setFilsd(d);
		a.setFilsg(g);
		return a;		
	}
	//Fonction qui récupère le code morse et le traduit en texte depuis l'arbre
	public static String morseToTexte(String chaine,Arbre arbre) throws IOException {
		String texte="";
		//récupération des parties entre les slashs
		int n = chaine.length() - chaine.replace("/", "").length();
		int index;
		for(int i = 0; i<n; i++) {
			//si la chaine contient un espace on affecte l'espace au texte
			if(chaine.substring(0,1)==" ") {
				texte = texte + " ";
			}
			//sinon on affecte les lettres traduites avec l'arbre au texte
			else {
				index = chaine.indexOf("/");
				texte = texte+Liste.recupTexte(chaine.substring(0,index), arbre);
                chaine = chaine.substring(index+1);  
			}
		}
		return texte;
	}
}
