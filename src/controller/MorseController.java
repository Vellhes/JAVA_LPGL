package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Trad;

public class MorseController implements Initializable{

    @FXML
    private Button btn_morse;

    @FXML
    private Button btn_txt;

    @FXML
    private TextField tf_trad;

    @FXML
    private Label lbl_langage;

    @FXML
    private Label lbl_trad;

    @FXML
    private Label lbl_condition;
    
    @FXML
    private Button btn_trad;

    private String txtAMorse="Vous traduisez du texte au morse";
    
    private String morseATxt="Vous traduisez du morse au texte";
    
    private String conditionTxtAMorse="Veuillez ne pas utiliser de caractères spéciaux (?,!,%,/,etc..), uniquement des lettres hors accent et cédille";
    
    private String conditionMorseATxt="Veuillez séparer chaque code morse par un caractère '/' sans placer d'espace";
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		btn_morse.setDisable(true);
		btn_txt.setDisable(false);
		lbl_langage.setText(txtAMorse);
		lbl_condition.setText(conditionTxtAMorse);
		lbl_trad.setText("");
		tf_trad.clear();
		
	}
	
	@FXML
	public void choixMorse() {
		
		btn_morse.setDisable(true);
		btn_txt.setDisable(false);
		lbl_langage.setText(txtAMorse);
		lbl_condition.setText(conditionTxtAMorse);
		lbl_trad.setText("");
		tf_trad.clear();
		
	}
	
	@FXML
	public void choixTxt() {
	
		btn_morse.setDisable(false);
		btn_txt.setDisable(true);
		lbl_langage.setText(morseATxt);
		lbl_condition.setText(conditionMorseATxt);
		lbl_trad.setText("");
		tf_trad.clear();
	}
	
	@FXML
	public void trad() throws IOException {
		if(btn_morse.isDisabled()==true && btn_txt.isDisabled()==false) {
			lbl_trad.setText(Trad.texteToMorse(tf_trad.getText()));
		}
		if(btn_morse.isDisabled()==false && btn_txt.isDisabled()==true) {
			lbl_trad.setText(Trad.morseToTexte(tf_trad.getText()));
		}
	}
	
	

    
    
}
