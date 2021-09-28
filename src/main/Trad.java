package main;
import java.io.IOException;

public class Trad {

	public static String texteToMorse(String chaine) throws IOException {
		String morse="";
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
	public static Arbre creerArbreMorse(String chaine,Liste l) throws IOException {
		String chaineG = chaine+".";
		String chaineD = chaine+"-";
		Arbre a = new Arbre();
		Arbre g = null;
		Arbre d = null;
		Morse morse = new Morse(Liste.recupTexte(chaine),chaine);
		if(Liste.rechercheListe(chaineD, l)==true) {
			d=creerArbreMorse(chaineD,l);
		}
		if(Liste.rechercheListe(chaineG, l)==true) {
			g=creerArbreMorse(chaineG,l);
		}
		
		a.setFeuille(morse);
		a.setFilsd(d);
		a.setFilsg(g);
		return a;		
	}
	public static String morseToTexte(String chaine,Arbre arbre) {
		String texte="";
		return texte;
	}
}
