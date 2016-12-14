package Arbre;

import java.util.List;

import sac.Item;

public class Arbre {
	Arbre sousAbGauche;
	Arbre sousAbDroit;
	List<Item> valeurNoeud;
	
	public Arbre(List<Item> items) {
		this.sousAbGauche = null;
		this.sousAbDroit = null;
		this.valeurNoeud = items;
	}
	
	public Arbre(Arbre sousArbreGauche, Arbre sousArbreDroit, List<Item> items) {
		this(items);
		this.sousAbGauche = sousArbreGauche;
		this.sousAbDroit = sousArbreDroit;
	}

	public Arbre getSousArbreGauche() {
		return sousAbGauche;
	}

	public void setSousArbreGauche(Arbre sousArbreGauche) {
		this.sousAbGauche = sousArbreGauche;
	}

	public Arbre getSousArbreDroit() {
		return sousAbDroit;
	}

	public void setSousArbreDroit(Arbre sousArbreDroit) {
		this.sousAbDroit = sousArbreDroit;
	}

	public List<Item> getValeurNoeud() {
		return valeurNoeud;
	}

	public void setValeur(List<Item> valeur) {
		this.valeurNoeud = valeur;
	}
	
	public void getFeuille(List<Arbre> feuilles) {
		if (this.getSousArbreDroit() == null && this.getSousArbreGauche() == null)
			feuilles.add(this);
		else {
			this.getSousArbreDroit().getFeuille(feuilles);
			this.getSousArbreGauche().getFeuille(feuilles);
		}
	}
	
	@Override
	public String toString() {
		return toString("\t");
	}
	
	public String toString(String s) {
		if (sousAbGauche != null) {
			if (sousAbDroit != null)
				return (s + valeurNoeud + "\n" + sousAbGauche.toString(s+"\t") + sousAbDroit.toString(s+"\t"));
			else
				return (s + valeurNoeud + "\n" + sousAbGauche.toString(s+"\t")+"\n");
		} else {
			if (sousAbDroit != null)
				return (s + valeurNoeud + "\n\n" + sousAbDroit.toString(s+"\t"));
			else
				return (s + valeurNoeud + "\n");
		}
	}
	
}
