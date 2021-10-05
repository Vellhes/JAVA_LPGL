package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import metiers.Arbre;
import metiers.Liste;
import metiers.Morse;
import metiers.Trad;

public class MorseController implements Initializable{

	@FXML
    private Button btn_effacer;

    @FXML
    private Button btn_ins;

    @FXML
    private Button btn_rech;

    @FXML
    private Button btn_suppr;

    @FXML
    private Button btn_versMorse;

    @FXML
    private Button btn_versTxt;

    @FXML
    private TextArea tf_morse;

    @FXML
    private TextArea tf_txt;

    @FXML
    private TextField txtf_codeins;

    @FXML
    private TextField txtf_coderech;

    @FXML
    private TextField txtf_codesuppr;
    
    @FXML
    private TextField txtf_lettreins;

    @FXML
    private TextField txtf_lettrerech;
    
    private String texte;
    
    private String morse;
    
    private Liste liste;
    
    private Arbre arbre;
    
    private char lettre;

    private String code;

    
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
    
    @FXML
    void chercheFichierTexte() throws IOException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choisir un fichier");
    	File fichier = fileChooser.showOpenDialog(null);
    	if(fichier!=null) {
    		File nouvFichier = new File("morseTrad.txt");
		    nouvFichier.createNewFile(); 
	    	FileReader fr = new FileReader(fichier);
	    	BufferedReader br = new BufferedReader(fr);
	    	String ligne;
	    	FileWriter fw = new FileWriter(nouvFichier);
	    	while((ligne=br.readLine())!=null){
	    		fw.write(Trad.texteToMorse(ligne)+" /");
	    	}
	    	fw.close();
	    	Alert alertFin = new Alert(AlertType.INFORMATION);
        	alertFin.setContentText("Votre fichier se trouve ici : "+nouvFichier.getAbsolutePath());
        	alertFin.showAndWait();
    	}
	   
    }
    
    @FXML
    void chercheFichierMorse() throws IOException{
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choisir un fichier");
    	File fichier = fileChooser.showOpenDialog(null);
    	if(fichier!=null) {
    		File nouvFichier = new File("texteTrad.txt");
    	    nouvFichier.createNewFile(); 
        	FileReader fr = new FileReader(fichier);
        	BufferedReader br = new BufferedReader(fr);
        	String ligne;
        	FileWriter fw = new FileWriter(nouvFichier);
        	while((ligne=br.readLine())!=null){
        		fw.write(Trad.morseToTexte(ligne, arbre)+" ");
        	}
        	fw.close();
        	Alert alertFin = new Alert(AlertType.INFORMATION);
        	alertFin.setContentText("Votre fichier se trouve ici : "+nouvFichier.getAbsolutePath());
        	alertFin.showAndWait();
    	}
    }
    
    @FXML
	void inserer(ActionEvent event) {
		if( txtf_lettreins.getText().equals("") || txtf_codeins.getText().equals("")) {

			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setContentText("Veuillez remplir les deux champs de texte");
			alerte.showAndWait();
		}else {
			lettre = txtf_lettreins.getText().charAt(0);
			code = txtf_codeins.getText();
			Morse m = new Morse(lettre,code);
			liste = Liste.ajoutListe(m,liste);
			System.out.println(liste);
		}
		txtf_lettreins.clear();
		txtf_codeins.clear();
	}
    @FXML
    void supprimer(ActionEvent event) {
    	code = txtf_codesuppr.getText();
    	liste = Liste.supprListe(code, liste);
    	System.out.println(liste);
		txtf_codesuppr.clear();
    }
    @FXML
    void rechercher(ActionEvent event) throws IOException {
    	Alert alerte = new Alert(AlertType.WARNING);
    	code ="";
    	lettre =' ';
    	if( txtf_lettrerech.getText().isEmpty()==false && txtf_coderech.getText().isEmpty()==false) {
			alerte.setContentText("Veuillez ne renseigner qu'un seul des deux champs a la fois");
    	}else if(txtf_lettrerech.getText().isEmpty()==true && txtf_coderech.getText().isEmpty()==true){
			alerte.setContentText("il n'y a pas de code morse où de lettre attribuée a votre recherche");
		}else{
			if(txtf_lettrerech.getText().isEmpty()==false && txtf_coderech.getText().isEmpty()==true) {
				lettre = txtf_lettrerech.getText().charAt(0);
				if(Liste.rechercheListeLettre(lettre, liste)) {
					alerte.setContentText("Le code morse qui correspond au caractère "+lettre +" est : " + Liste.recupMorse(lettre));
				}
				else {
					alerte.setContentText("Le caractère "+lettre+" n'existe pas dans la liste");
				}
			}
			else {
				code = txtf_coderech.getText();
				if(Liste.rechercheListe(code, liste)) {
					alerte.setContentText("Le caractère qui correspond au code morse est : " + Liste.recupTexte(code, arbre));
				}
				else {
					alerte.setContentText("Le code "+code+" n'existe pas dans la liste");
				}
			}
    	}
    	alerte.showAndWait();
    	txtf_lettrerech.clear();
		txtf_coderech.clear();
    }

	

}
