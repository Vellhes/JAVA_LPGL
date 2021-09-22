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
	
}
