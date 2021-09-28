package main;

import java.io.IOException;

public class Main{
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("../fxml/Morse.fxml"));
//		primaryStage.setTitle("Traducteur Mores");
//		primaryStage.setScene(new Scene(root,600,400));
//		primaryStage.show();
//	}
	
	public static void main(String[] args) throws IOException {
		
		Liste l = Liste.creerListe();
		Arbre arbre = Trad.creerArbreMorse("", l);
		System.out.println(arbre);
		
	}
}