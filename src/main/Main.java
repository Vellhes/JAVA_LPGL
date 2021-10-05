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
		//1. Construire une liste de conversion à partir d'un fichier qui contient le code morse
		//Création de la liste
		Liste l = Liste.creerListe();
		System.out.println(l);
		
		//2. Effectuer des opérations de base sur cette liste de conversion
		//2.1 Ajout d'un code dans la liste
		Morse morse = new Morse('?',"..--");
		l = Liste.ajoutListe(morse, l);
		System.out.println("Ajout : "+l);
		
		//2.2 Suppression d'un code
		l = Liste.supprListe("....", l);
		System.out.println("Suppression : "+ l);
		
		//2.3 Recherche d'un code (vérification de sa présence)
		boolean check;
		check = Liste.rechercheListe(".--", l);
		if(check) System.out.println(".-- Présent dans la liste");
		else System.out.println(".-- Absent de la liste");
		
		Arbre arbre = Trad.creerArbreMorse("", Liste.creerListe());
		System.out.println(arbre);
		System.out.println(Trad.morseToTexte(".../---/.../ /-/...././---/", arbre));
		
		launch(args);
		
		
	}
}
