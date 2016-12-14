package sac;

public class Item implements Comparable<Item> {
	private String nom;
	private int valeur, poids;

	public Item(String n, int p, int v) {
		this.nom = n;
		this.poids = p;
		this.valeur = v;
	}

	public String getNom() {
		return nom;
	}

	public int getValeur() {
		return valeur;
	}

	public int getPoids() {
		return poids;
	}

	@Override
	public String toString() {
		return "" + nom + " ; " + valeur + " ; " + poids;
	}
	
	/*
	 * compareTo() Methode comparant les rapports (valeur/poids) de deux objets
	 */
	@Override
	public int compareTo(Item arg0) {
		float monrapport = valeur / poids;
		float objetRapport = arg0.getValeur() / arg0.getPoids();
		if (monrapport == objetRapport)
			return 0;
		if (monrapport > objetRapport)
			return 1;
		else
			return -1;
	}

}
