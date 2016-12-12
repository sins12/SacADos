import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Arbre.Arbre;
import sac.Item;
import sac.SacADos;

public class Appli {

	public static void main(String[] args) {
		Item i1 = new Item("a", 1.0f, 1.0f);
		Item i2 = new Item("b", 2.0f, 2.0f);
		Item i3 = new Item("c", 3.0f, 3.0f);
		Item i4 = new Item("d", 4.0f, 4.0f);
		
		List<Item> list = new ArrayList<>();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);
		
		SacADos sac = new SacADos(list);
		
		Arbre b = sac.créerArbre(new Arbre(new ArrayList<Item>()), list.listIterator().nextIndex());
		System.out.println(b.toString());
	}

}
