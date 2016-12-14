package sac;

public class Item implements Comparable<Item> {
	private String nom;
	private double valeur, poids;

	public Item(String n, double p, double v) {
		this.nom = n;
		this.poids = p;
		this.valeur = v;
	}

	public String getNom() {
		return nom;
	}

	public double getValeur() {
		return valeur;
	}

	public double getPoids() {
		return poids;
	}

	@Override
	public String toString() {
		return "" + nom + " ; " + poids + " ; " + valeur;
	}
	
	/*
	 * compareTo() Methode comparant les rapports (valeur/poids) de deux objets
	 */
	@Override
	public int compareTo(Item arg0) {
		double monrapport = valeur / poids;
		double objetRapport = arg0.getValeur() / arg0.getPoids();
		if (monrapport == objetRapport)
			return 0;
		if (monrapport > objetRapport)
			return 1;
		else
			return -1;
	}

}
