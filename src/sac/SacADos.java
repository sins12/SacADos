package sac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import Arbre.Arbre;

public class SacADos {
	private List<Item> tousLesObjets;
	private Float capacité;
	private List<Item> contenu;

	// Constructeur générant un sac à dos vide
	public SacADos() {
		this.capacité = null;
		this.contenu = new ArrayList<Item>();
	}
	
	//temporaire
	public SacADos(List<Item> items) {
		this.tousLesObjets = items;
	}

	/*
	 * Constructeur générant un sac à dos vide, stockant la liste des objets
	 * possibles et le poids maximal autorisé du sac
	 */
	public SacADos(String chemin, float poidsMaximal) {
		this.capacité = poidsMaximal;
		this.contenu = new ArrayList<Item>();
		this.tousLesObjets = new ArrayList<Item>();
	}

	/*
	 * Switch de choix de methode pour resolution du problème.
	 */
	private void methodeUtilise(int methode) {
		switch (methode) {
		case 1:
			gloutonne();
			break;
		case 2:
			progDynamique();
			break;
		case 3:
			pse();
			break;
		}
	}

	/*
	 * Calcul du poids total du sac à dos en fonction du poids des objets de la
	 * liste "contenu"
	 */
	public float poidsTotal() {
		float p = 0;
		for (Item i : this.contenu)
			p += i.getPoids();
		return p;
	}

	/*
	 * Calcul de la valeur total du sac à dos en fonction de celle des objets de
	 * la liste "contenu"
	 */
	public float valeurTotale() {
		float val = 0;
		for (Item i : contenu)
			val += i.getValeur();
		return val;
	}

	private void pse() {
		Arbre b = créerArbre(new Arbre(new ArrayList<Item>()), tousLesObjets.listIterator().nextIndex());
	}

	public Arbre créerArbre(Arbre ab, int i) {
		if(i >= tousLesObjets.size()) {
			return ab;
		}
		
		ab.setSousArbreGauche(créerArbre(new Arbre(ab.getValeur()), i + 1));
		List<Item> valeur = new ArrayList<>(ab.getValeur());
		valeur.add(tousLesObjets.get(i));
		ab.setSousArbreDroit(créerArbre(new Arbre(valeur), i + 1));
		return ab;
	}
	
	public Arbre setSousArbres(Arbre src, List<Item> g, List<Item> d) {
		src.setSousArbreGauche(new Arbre(g));
		src.setSousArbreDroit(new Arbre(d));
		return src;
	}

	private void progDynamique() {
		/*
		 * Création d'une matrice de flottants pour récupérer les poids des
		 * valeurs dans les cases de la matrice en fonction du poids de l'objet
		 * en ligne : objets, valeur dans les cases en colonne : poids jusqu'a
		 * "capacité", poids max du sac
		 */
		float M[][] = new float[this.tousLesObjets.size()][(int) (this.capacité + 1)];
		/*
		 * Pour tous les objets potentiels du sac : Initialisation du 1er objet
		 * La valeur du 1ere objet est mis dans la case si son poids est
		 * supérieur au poids maximal autorisé, sinon 0
		 */
		for (int i = 0; i < this.capacité; i++) {
			if (this.tousLesObjets.get(0).getPoids() > i)
				M[0][i] = 0;
			else
				M[0][i] = tousLesObjets.get(0).getValeur();
		}
		/*
		 * On remplit le tableau de gauche à droite tant que la capacité du sac
		 * est supérieur au nombre d'occurence du poids de l'objet. Dans chaque
		 * case du tableau : Si le poids de l'objet i est supérieur au poids
		 * maximal autorisé : la valeur de l'objet prend celle de valeur de la
		 * case d'au dessus (dans la même colonne). Sinon : la valeur de l'objet
		 * prend la valeur maximale entre celle de valeur de la case d'au dessus
		 * (dans la m�me colonne)(i-1) et celle de l'objet précédent dont le
		 * poids est poids de l'objet + valeur de l'objet de la ligne (i)
		 */
		for (int i = 1; i < M.length; i++) {
			for (int j = 0; j < this.capacité; j++) {
				if ((tousLesObjets.get(i).getPoids()) * 10 > j * 10)
					M[i][j] = M[i - 1][j];
				else
					M[i][j] = Math.max(M[i - 1][j], M[i - 1][(int) (j - this.tousLesObjets.get(i).getPoids())]
							+ this.tousLesObjets.get(i).getValeur());
			}
		}
		/*
		 * Recuperation du poids minimal nécessaire pour faire la valeur
		 * optimale
		 */
		int i = M.length;
		int j = (int) (this.getCapacité() * 10);
		while (M[i][j / 10] == M[i][(j / 10) - 1])
			j -= 10;
		/*
		 * Tant que la capacité du sac > 0 : Tant que la taille de la matrice >
		 * 0 ET valeur de l'objet de i = valeur de l'objet de i-1 (valeur Objet
		 * = valeur de la case d'au dessus (dans la même colonne))
		 *
		 * la capacité - le poids de l'objet Si la capacité est supérieur ou
		 * égale à 0 On ajoute l'objet i à la liste des objets contenu (dans le
		 * sac)
		 */
		while (j / 10 > 0) {
			while (i > 0 && M[i][j] == M[i - 1][j]) {
				--i;
			}
			j = j - (int) (tousLesObjets.get(i).getPoids());

			if (j >= 0) {
				this.contenu.add(tousLesObjets.get(i));
				i--;
			}
		}
	}

	/*
	 * Methode Gloutonne Calcul rapport (v/p) pour chaque objet grâce à la
	 * methode CompareTo dans la classe Item implémentant Comparable Trie par
	 * ordre decroissant de la liste "tous" qui contient "tousLesObjets" Boucle
	 * d'ajout des objets
	 */
	private void gloutonne() {
		/*
		 * float f; for(int i=0; i < this.tousLesObjets.size(); ++i){ f =
		 * tousLesObjets.get(i).getValeur()/tousLesObjets.get(i).getPoids();
		 */
		ArrayList<Item> tous = new ArrayList<Item>(tousLesObjets);
		Collections.sort(tous, Collections.reverseOrder());

		/*
		 * Pour tous les objets de la liste "tous", on ajoute l'objet si le poid
		 * maximal est respecté Choisir entre les deux boucles ci dessous
		 */
		for (int a = 0; a < tous.size(); ++a) {
			if (this.poidsTotal() + tous.get(a).getPoids() <= this.getCapacité())
				this.contenu.add(tous.get(a));
		}

		int i = tous.size() - 1;
		while (i >= 0) {
			if (this.poidsTotal() + tous.get(i).getPoids() <= this.getCapacité())
				this.contenu.add(tous.get(i));
			else
				++i;
		}
	}

	public Float getCapacité() {
		return capacité;
	}
	
	public String toString() {
		String s = "";
		s += "Sac à dos de poids maximal " + getCapacité() + " kg";
		s += System.lineSeparator();
		s += "Sac à dos de poids " + poidsTotal() + " kg";
		s += System.lineSeparator();
		s += "Sac à dos de valeur totale " + valeurTotale() + " points";
		s += System.lineSeparator();
		s += "Contenu du sac : " + System.lineSeparator();
		for (Item i : contenu)
			s += i + System.lineSeparator();
		return s;
	}
}
