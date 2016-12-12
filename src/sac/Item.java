package sac;

public class Item implements Comparable<Object> {
	private String nom;
	private float valeur, poids;

	public Item(String n, float v, float p) {
		this.nom = n;
		this.valeur = v;
		this.poids = p;
	}

	public String getNom() {
		return nom;
	}

	public float getValeur() {
		return valeur;
	}

	public float getPoids() {
		return poids;
	}

	@Override
	public String toString() {
		return "" + nom;//+ " ; " + valeur + " ; " + poids;
	}

	/*
	 * compareTo() Methode comparant les rapports (valeur/poids) de deux objets
	 */
	@Override
	public int compareTo(Object o) {
		float monrapport = valeur / poids;
		float objetRapport = ((Item) o).getValeur() / ((Item) o).getPoids();
		if (monrapport == objetRapport)
			return 0;
		if (monrapport > objetRapport)
			return 1;
		else
			return -1;
	}

}
