package main;
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
	    	char lettre = ligne.charAt(0);
	    	ligne = ligne.substring(4);
	        Morse morse = new Morse(lettre,ligne);
	        liste = new Liste(morse,p);
	        p=liste;
	      }
	      fr.close();
		return liste;  
	}
	
	public static String recupMorse(char c) throws IOException {
		Liste liste = creerListe();
		c=Character.toUpperCase(c);
		while(liste.morse.lettre != c) {
			liste = liste.suite;
		}
		return liste.morse.code;
	}
	
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
	 
	public static char recupTexte(String code, Arbre arbre) throws IOException {
		 int i = 0;
		 char lettre = ' ';
		 while(i!=code.length()) {
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

	public static Liste ajoutListe(Morse morse, Liste liste) {
		Liste liste2 = new Liste(morse,liste);
		return liste2;
	}
	
	public static Liste supprListe(String code, Liste liste) {
		boolean verif = rechercheListe(code,liste);
		
		return liste;
	}
}


