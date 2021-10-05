package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import metiers.Arbre;
import metiers.Liste;
import metiers.Trad;

public class MorseController implements Initializable{

    @FXML
    private Button btn_effacer;

    @FXML
    private TextArea tf_morse;

    @FXML
    private TextArea tf_txt;
    
    private String texte;
    
    private String morse;
    
    private Liste liste;
    
    private Arbre arbre;
    
    @Override
    // Fonction d'initialisation
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			liste = Liste.creerListe();
			arbre = Trad.creerArbreMorse("", liste);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		effacer(null);
	}

    //Fonction qui efface le texte des textArea
    @FXML
    void effacer(ActionEvent event) {
    	tf_txt.clear();
    	tf_morse.clear();
    }

    //Fonction qui récupère le texte, le traduit et l'insere dans le textArea du morse
    @FXML
    void versMorse(KeyEvent event) throws IOException {
    	texte=tf_txt.getText();
    	morse=Trad.texteToMorse(texte);
    	tf_morse.setText(morse);
    }

    //Fonction qui récupère le code morse, le traduit et l'insère dans le textArea du texte
    @FXML
    void versTexte(KeyEvent event) throws IOException {
    	morse=tf_morse.getText();
    	texte=Trad.morseToTexte(morse,arbre);
    	tf_txt.setText(texte);
    }

	

}
