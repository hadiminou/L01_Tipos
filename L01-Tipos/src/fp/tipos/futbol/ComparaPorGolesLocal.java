package fp.tipos.futbol;

import java.util.Comparator;

public class ComparaPorGolesLocal implements Comparator<Partido> {

	@Override
	public int compare(Partido o1, Partido o2) {
		/*
		 * si o1>o2: +
		 * si o1<o2: -
		 * si =:0
		 */
		return o1.golesLocal().compareTo(o2.golesLocal());
	}

}
