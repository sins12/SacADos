package Arbre;

import java.util.List;

import sac.Item;

public class Arbre {
	Arbre sousArbreGauche;
	Arbre sousArbreDroit;
	List<Item> items;
	
	public Arbre(List<Item> items) {
		this.sousArbreGauche = null;
		this.sousArbreDroit = null;
		this.items = items;
	}
	
	public Arbre(Arbre sousArbreGauche, Arbre sousArbreDroit, List<Item> items) {
		this(items);
		this.sousArbreGauche = sousArbreGauche;
		this.sousArbreDroit = sousArbreDroit;
	}

	public Arbre getSousArbreGauche() {
		return sousArbreGauche;
	}

	public void setSousArbreGauche(Arbre sousArbreGauche) {
		this.sousArbreGauche = sousArbreGauche;
	}

	public Arbre getSousArbreDroit() {
		return sousArbreDroit;
	}

	public void setSousArbreDroit(Arbre sousArbreDroit) {
		this.sousArbreDroit = sousArbreDroit;
	}

	public List<Item> getValeur() {
		return items;
	}

	public void setValeur(List<Item> valeur) {
		this.items = valeur;
	}
	
	@Override
	public String toString() {
		return toString("\t");
	}
	
	public String toString(String s) {
		if (sousArbreGauche != null) {
			if (sousArbreDroit != null)
				return (s + items + "\n" + sousArbreGauche.toString(s+"\t") + sousArbreDroit.toString(s+"\t"));
			else
				return (s + items + "\n" + sousArbreGauche.toString(s+"\t")+"\n");
		} else {
			if (sousArbreDroit != null)
				return (s + items + "\n\n" + sousArbreDroit.toString(s+"\t"));
			else
				return (s + items + "\n");
		}
	}
	
}
