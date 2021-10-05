package metiers;
import java.io.*;


public class Liste {
	Morse morse;
	Liste suite;
	
	public Liste() {
		super();
	}
	public Morse getMorse() {
		return morse;
	}
	public void setMorse(Morse morse) {
		this.morse = morse;
	}
	public Liste getSuite() {
		return suite;
	}
	public void setSuite(Liste suite) {
		this.suite = suite;
	}
	public Liste(Morse morse, Liste suite) {
		super();
		this.morse = morse;
		this.suite = suite;
	}
	
	@Override
	public String toString() {
		return "Morse=" + morse + ", suite=" + suite + "";
	}
	// Fonction qui instancie la liste a partir du fichier morse.txt
	public static Liste creerListe() throws IOException {
		File file = new File("src/morse.txt");    
	      // Créer l'objet File Reader
	      FileReader fr = new FileReader(file);  
	      // Créer l'objet BufferedReader        
	      BufferedReader br = new BufferedReader(fr);      
	      String ligne;
	      Liste liste = null;
	      Liste p = null;
	      while((ligne = br.readLine()) != null)
	      {
	    	//récupère la lettre en début de ligne  
	    	char lettre = ligne.charAt(0);
	    	//récupère le code correspondant
	    	ligne = ligne.substring(4);
	    	//créé l'objet morse connectant le code et la lettre
	        Morse morse = new Morse(lettre,ligne);
	        // affectation a la liste
	        liste = new Liste(morse,p);
	        p=liste;
	      }
	      fr.close();
		return liste;  
	}
	//Fonction qui retourne le code morse d'une lettre donnée a partir de la liste.
	public static String recupMorse(char c) throws IOException {
		Liste liste = creerListe();
		//passage en majuscule pour éviter les erreurs
		c=Character.toUpperCase(c);
		while(liste.morse.lettre != c) {
			liste = liste.suite;
		}
		return liste.morse.code;
	}
	// Fonction qui retourne la lettre liée au code morse depuis la liste
	public static char recupChar(String code) throws IOException {
	        Liste liste = creerListe();
	        while(liste!=null) {
	        	String codeM = liste.morse.code;
	        	if(codeM.equals(code)) {
	        		return liste.morse.lettre;
	        	}
	            liste = liste.suite;
	        }
	        return ' ';
	                
	    }
	 //Fonction qui renvoie la lettre liée au code morse en utilisant l'arbre binaire
	public static char recupTexte(String code, Arbre arbre) throws IOException {
		 int i = 0;
		 char lettre = ' ';
		 while(i!=code.length()) {
			 	//décomposition du code morse signal par signal
	        	if(code.charAt(i)=='.') {
	        		arbre=arbre.filsg;
	        	}
	        	else if(code.charAt(i)=='-') {
	        		arbre=arbre.filsd;
	        	}
	        	i++;
	        	lettre = arbre.feuille.lettre;
	        }
	        return lettre;
	                
	    }
	 //Cette fonction renvoie un booléen, a true si le code se trouve dans la liste, a false si il ne s'y trouve pas
	public static boolean rechercheListe(String code, Liste l) {
		while(l!=null) {
			String codeM = l.morse.code;
        	if(codeM.equals(code)) {
        		return true;
        	}
			l=l.suite;
		}
		return false;
	}
	//Fonction qui retourne une liste après un ajout en Tête
	public static Liste ajoutListe(Morse morse, Liste liste) {
		Liste liste2 = new Liste(morse,liste);
		return liste2;
	}
	//Fonction qui supprime un element de la liste
	public static Liste supprListe(String code, Liste liste) {
		//vérification de la présence du code dans la liste
		boolean verif = rechercheListe(code,liste);
		Liste p = liste;
		Liste r = p;
		if(verif==true) {
			while(p!=null) {
				String codeM = p.morse.code;
	        	if(codeM.equals(code)) {
	        		p=p.suite;
	        		r.suite=p;
	        		return liste;
	        	}
	        	r=p;
				p=p.suite;
			}
		}
		return liste;
	}
}


