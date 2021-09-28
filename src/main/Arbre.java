package main;

public class Arbre {
	Arbre filsg;
	Morse feuille;
	Arbre filsd;
	
	public Arbre getFilsg() {
		return filsg;
	}
	public void setFilsg(Arbre filsg) {
		this.filsg = filsg;
	}
	public Morse getFeuille() {
		return feuille;
	}
	public void setFeuille(Morse feuille) {
		this.feuille = feuille;
	}
	public Arbre getFilsd() {
		return filsd;
	}
	public void setFilsd(Arbre filsd) {
		this.filsd = filsd;
	}
	public Arbre(Arbre filsg, Morse feuille, Arbre filsd) {
		super();
		this.filsg = filsg;
		this.feuille = feuille;
		this.filsd = filsd;
	}
	public Arbre() {
		super();
	}
	@Override
	public String toString() {
		return "Arbre [filsg=" + filsg + ", feuille=" + feuille + ", filsd=" + filsd + "]";
	}
	
}
