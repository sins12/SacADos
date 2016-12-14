package sac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Arbre.Arbre;

public class SacADos {
	private List<Item> tousLesObjets;
	private int capacité;
	private List<Item> contenu;

	// Constructeur générant un sac à dos vide
	public SacADos() {
		this.capacité = 0;
		this.contenu = new ArrayList<Item>();
	}

	// temporaire
	public SacADos(List<Item> items, int poidsMaximal) {
		this.capacité = poidsMaximal;
		this.tousLesObjets = items;
		contenu = new ArrayList<Item>();
	}

	/*
	 * Constructeur générant un sac à dos vide, stockant la liste des objets
	 * possibles et le poids maximal autorisé du sac
	 */
	public SacADos(String chemin, int poidsMaximal) {
		this.capacité = poidsMaximal;
		this.contenu = new ArrayList<Item>();
		this.tousLesObjets = new ArrayList<Item>();
	}

	/*
	 * Calcul du poids total du sac à dos en fonction du poids des objets de la
	 * liste "contenu"
	 */
	public int poidsTotal() {
		int p = 0;
		for (Item i : this.contenu)
			p += i.getPoids();
		return p;
	}

	/*
	 * Calcul de la valeur total du sac à dos en fonction de celle des objets de
	 * la liste "contenu"
	 */
	public int valeurTotale() {
		int val = 0;
		for (Item i : contenu)
			val += i.getValeur();
		return val;
	}

	public List<Item> getContenu() {
		return new ArrayList<Item>(contenu);
	}

	public void résoudre_pse() {
		résoudre_gloutonne();
		int borneInf = valeurTotale();
		contenu.clear();
		Arbre b = créerArbre(new Arbre(new ArrayList<Item>()), tousLesObjets.listIterator().nextIndex(), borneInf);
		System.out.println(b);
		List<Arbre> feuilles = new ArrayList<>();
		b.getFeuille(feuilles);
		for(int i = 0; i < feuilles.size(); i++) {
			int totalVal = 0;
			for (int j = 0; j < feuilles.get(i).getValeurNoeud().size(); j++){
				totalVal += feuilles.get(i).getValeurNoeud().get(j).getValeur();
				System.out.println(totalVal);
			}
			if (totalVal > valeurTotale() || (totalVal == valeurTotale() && feuilles.get(i).getValeurNoeud().size() < contenu.size()))
				contenu = feuilles.get(i).getValeurNoeud();
		}
	}

	public Arbre créerArbre(Arbre ab, int i, int borneInf) {
		int poidsNoeud = 0;
		int borneSup = 0;
		if (i >= tousLesObjets.size()) {
			return ab;
		}
		for (int j = 0; j < ab.getValeurNoeud().size(); j++) {
			poidsNoeud += ab.getValeurNoeud().get(j).getPoids(); // poids total du noeud
			borneSup += ab.getValeurNoeud().get(j).getValeur(); // borneSup contient la valeur totale du noeud
			if (borneSup > borneInf)
				borneInf = borneSup;
		}
		for (int j = i; j < tousLesObjets.size(); j++) {
			borneSup += tousLesObjets.get(j).getValeur();// on ajoute le poids des éléments susceptibles d'être rajoutés
		}
		if (poidsNoeud < capacité && borneInf < borneSup) { // si le poids n'a pas été dépassé et que la borne Supérieure est supérieure à la borne inférieure alors on continue la recherche
			ab.setSousArbreGauche(créerArbre(new Arbre(ab.getValeurNoeud()), i + 1, borneInf));
			List<Item> valeur = new ArrayList<>(ab.getValeurNoeud());
			valeur.add(tousLesObjets.get(i));
			ab.setSousArbreDroit(créerArbre(new Arbre(valeur), i + 1, borneInf));
			return ab;
		}
		else return ab;
	}

	public void résoudre_progDynamique() {
		/*
		 * Création d'une matrice de flottants pour récupérer les poids des
		 * valeurs dans les cases de la matrice en fonction du poids de l'objet
		 * en ligne : objets, valeur dans les cases en colonne : poids jusqu'a
		 * "capacité", poids max du sac
		 */
		int M[][] = new int[this.tousLesObjets.size()][this.capacité + 1];
		/*
		 * Pour tous les objets potentiels du sac : Initialisation du 1er objet
		 * La valeur du 1ere objet est mis dans la case si son poids est
		 * supérieur au poids maximal autorisé, sinon 0
		 */
		for (int j = 0; j < this.capacité + 1; j++) {
			if (this.tousLesObjets.get(0).getPoids() > j)
				M[0][j] = 0;
			else
				M[0][j] = tousLesObjets.get(0).getValeur();
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
			for (int j = 0; j < M[i].length; j++) {
				if ((tousLesObjets.get(i).getPoids()) > j)
					M[i][j] = M[i - 1][j];
				else
					M[i][j] = Math.max(M[i - 1][j],
							M[i - 1][j - this.tousLesObjets.get(i).getPoids()] + this.tousLesObjets.get(i).getValeur());
			}
		}
		/*
		 * Recuperation du poids minimal nécessaire pour faire la valeur
		 * optimale
		 */
		int i = M.length - 1;
		int j = M[i].length - 1;
		while (M[i][j] == M[i][j - 1]) // Maximiasation de la valeur pour un minimum de poids
			--j;
		/*
		 * Tant que la capacité du sac > 0 : Tant que la taille de la matrice >
		 * 0 ET valeur de l'objet de i = valeur de l'objet de i-1 (valeur Objet
		 * = valeur de la case d'au dessus (dans la même colonne))
		 *
		 * la capacité - le poids de l'objet Si la capacité est supérieur ou
		 * égale à 0 On ajoute l'objet i à la liste des objets contenu (dans le
		 * sac)
		 */
		while (j > 0) {
			while (i > 0 && M[i][j] == M[i - 1][j]) {
				--i;
			}
			j = j - (tousLesObjets.get(i).getPoids());

			if (j >= 0) {
				this.contenu.add(tousLesObjets.get(i));
				--i;
			}
		}
	}

	/*
	 * Methode Gloutonne Calcul rapport (v/p) pour chaque objet grâce à la
	 * methode CompareTo dans la classe Item implémentant Comparable Trie par
	 * ordre decroissant de la liste "tous" qui contient "tousLesObjets" Boucle
	 * d'ajout des objets
	 */
	public void résoudre_gloutonne() {
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
	}

	public int getCapacité() {
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
