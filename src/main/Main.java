package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import metiers.Arbre;
import metiers.Liste;
import metiers.Morse;
import metiers.Trad;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../fxml/Morse.fxml"));
		primaryStage.setTitle("Traducteur Mores");
		primaryStage.setScene(new Scene(root,600,400));
		primaryStage.show();
	}
	
	public static void main(String[] args) throws IOException {
		
		Liste l = Liste.creerListe();
		Arbre arbre = Trad.creerArbreMorse("", l);
		System.out.println(arbre);
		System.out.println(Trad.morseToTexte(".../---/.../ /-/...././---/", arbre));
		System.out.println(l);
		Morse morse = new Morse('?',"..--");
		l = Liste.ajoutListe(morse, l);
		System.out.println(l);
		l = Liste.supprListe("....", l);
		System.out.println("Suppression : "+ l);
	}
}
