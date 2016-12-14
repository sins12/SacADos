package sac;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SacADosTest {

	@Test
	public void testRésoudre_gloutonne() {
		Item a = new Item("Lampe", 2, 30);
		Item b = new Item("Sac de couchage", 1, 20);
		Item c = new Item("Camping gaz", 3, 40);
		Item d = new Item("Couteau suisse", 1, 50);
		
		ArrayList<Item> objets = new ArrayList<>();
		objets.add(a);
		objets.add(b);
		objets.add(c);
		objets.add(d);
		
		SacADos sac = new SacADos(objets, 3);
		sac.résoudre_gloutonne();
		assertEquals(sac.poidsTotal(), 2);
		assertEquals(sac.valeurTotale(), 70);
		
	}

	@Test
	public void testRésoudre_progDynamique() {
		Item a = new Item("Lampe", 2, 30);
		Item b = new Item("Sac de couchage", 1, 20);
		Item c = new Item("Camping gaz", 3, 40);
		Item d = new Item("Couteau suisse", 1, 50);
		
		ArrayList<Item> objets = new ArrayList<>();
		objets.add(a);
		objets.add(b);
		objets.add(c);
		objets.add(d);
		
		SacADos sac = new SacADos(objets, 3);
		sac.résoudre_progDynamique();
		assertEquals(sac.poidsTotal(), 3);
		assertEquals(sac.valeurTotale(), 80);
	}
	
	@Test
	public void testRésoudre_pse() {
		Item a = new Item("Lampe", 2, 30);
		Item b = new Item("Sac de couchage", 1, 20);
		Item c = new Item("Camping gaz", 3, 40);
		Item d = new Item("Couteau suisse", 1, 50);
		
		ArrayList<Item> objets = new ArrayList<>();
		objets.add(a);
		objets.add(b);
		objets.add(c);
		objets.add(d);
		
		SacADos sac = new SacADos(objets, 3);
		sac.résoudre_pse();
		assertEquals(sac.poidsTotal(), 3);
		assertEquals(sac.valeurTotale(), 80);
	}
}
