package fp.tipos.futbol.test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.stream.Stream;
import fp.tipos.futbol.ComparaPorGolesLocal;
import fp.tipos.futbol.Competicion;
import fp.tipos.futbol.FactoriaFutbol;
import fp.tipos.futbol.Partido;
import fp.tipos.futbol.PartidoFutbol;
import fp.tipos.futbol.ResultadoQuiniela;

public class TestFutbol {

	public static void main(String[] args) {
		
		Competicion cmpt = FactoriaFutbol.leeFichero("D:\\Hadi\\Documents\\Uni\\FP\\Java\\"
				+ "eclipse-java-2023-12-R-win32-x86_64\\eclipse\\eclipse-workspace\\Laboratorio\\"
				+ "L01-Tipos\\data\\partidos.csv", "La Liga", 2018);
		
		for (Partido p : cmpt.partidos()) {
			//System.out.println(p.cadenaConFormato());
		}
		
		//System.out.println("\nvictorias visitantes: " + cmpt.getVictoriasVisitantes());
		//System.out.println("\nTotal goles marcados: " +cmpt.getTotalGolesMarcados("Real Madrid"));
		//System.out.println("\nconjunto de equipos: " + cmpt.getEquipos());
		//System.out.println("\npartido con mas goles: " + cmpt.getPartidoMasGoles());
		
		//System.out.println("\npartidos ordenados por goles");
		List<Partido> partidos = cmpt.partidos();
		Collections.sort(partidos, new ComparaPorGolesLocal());
		//System.out.println(partidos);
		
		SortedSet<Partido> ss = cmpt.obtenPartidosOrdenadosPorGoles();
		//System.out.println("\nTamano del conjunto de partidos: " + ss.size());
		
		SortedSet<Partido> ss2 = cmpt.obtenPartidosOrdenadosPorTamanoNombreEquipos();
		//System.out.println("\nTamano del nombre de equipos: " + ss2.size());
		
		//System.out.println("\nAgrupa partidos por goles locales:");
		SortedMap<Integer, SortedSet<Partido>> sm = cmpt.agrupaPartidosPorGolesLocal();
		for (Integer k : sm.keySet()) {
			//System.out.println(k + " -> " + sm.get(k));
		}
		//System.out.println(cmpt.agrupaPartidosPorGolesLocal());
		
		//System.out.println("\npartido que empieza antes de entre los que el equipo local"
		//		+ " ha marcado 5 goles");
		//System.out.println(cmpt.obtenPartidoEmpiezaAntes(5));
		
		//System.out.println("\nel equipo que ha marcado mas goles: ");
		//System.out.println(cmpt.obtenEquipoQueHaMarcadoMasGoles());
		
		/***tests de stream***/
		
		//System.out.println("\ndiferencia mas de dos goles: ");
		//cmpt.printStream(cmpt.filtrarPorDiferenciaGoles());
		
		//System.out.println("\nordenarPorFecha:");
		//cmpt.printStream(cmpt.ordenarPorFecha());
		
		//System.out.println("\nobtenerDiasJugados:");
		//cmpt.printStream(cmpt.obtenerDiasJugados());
		
		//System.out.println("\nfiltrar por equipo: ");
		//cmpt.printStream(cmpt.filtrarPorEquipo("Barcelona"));
		
		//System.out.println("\nfiltrar por resultado: ");
		//cmpt.printStream(cmpt.filtrarPorResultado(ResultadoQuiniela.DOS));
		
		//System.out.println("\nobtener Equipo Local Unicos: ");
		//cmpt.printStream(cmpt.obtenerEquipoLocalUnicos());
		
		//System.out.println("\nobtenerYOrdenarEquiposLocal");
		//cmpt.printStream(cmpt.obtenerYOrdenarEquiposLocal());
		
		//System.out.println("\nfiltrarYOrdenarPorEquipoYFecha");
		//cmpt.printStream(cmpt.filtrarYOrdenarPorEquipoYFecha("Barcelona"));
		
		//System.out.println("\nobten1X2");
		//cmpt.printStream(cmpt.obten1X2());
		
		//System.out.println("\nobtenGolesPrimo");
		//cmpt.printStream(cmpt.obtenGolesPrimo());
		
		
		/*********************/
		//System.out.println("\ntodosJugadosEn");
		//System.out.println(cmpt.todosJugadosEn(2019));
		
		//System.out.println("\nalgunoJugadoEnFecha");
		//System.out.println(cmpt.algunoJugadoEnFecha(LocalDate.of(2019,3,2)));
		//System.out.println(cmpt.algunoJugadoEnFecha(LocalDate.now()));
		
		//System.out.println("\nnumPartidos");
		//System.out.println((cmpt.numPartidos()));
		
		//System.out.println("\ncualquierPartido");
		//System.out.println(cmpt.cualquierPartido());
		
		//System.out.println("\nprimerPartido");
		//System.out.println(cmpt.primerPartido());
		
		//System.out.println("\nimprimeConFormato");
		//cmpt.imprimeConFormato();
		
		//System.out.println("\npartidoMayorDiferencia");
		//System.out.println(cmpt.partidoMayorDiferencia());
		
		//System.out.println("\nnumGolesMarcados");
		//System.out.println(cmpt.numGolesMarcados());
		
		//System.out.println("\ntodosDiferenciaEntre");
		//System.out.println(cmpt.todosDiferenciaEntre(4, 6));
		
		//System.out.println("\nalgunoConGolesTotales");
		//System.out.println(cmpt.algunoConGolesTotales(4));
		
		//System.out.println("\nnumFechasDistintas");
		//System.out.println(cmpt.numFechasDistintas());
		
		//System.out.println("\nequipoLocalNgoles");
		//System.out.println(cmpt.equipoLocalNgoles(4));
		
		//System.out.println("\nimprimeGolesTotales");
		//cmpt.imprimeGolesTotales();
		
		//System.out.println("\npartidoMasAntiguo");
		//System.out.println(cmpt.partidoMasAntiguo());
		
		//System.out.println("\npromedioGolesPorEquipo");
		//Map<String, Double> d = cmpt.promedioGolesPorEquipo();
		//for (String eq: d.keySet()) {
		//	System.out.println(eq + " -> " + d.get(eq));
		//}
		
		//System.out.println("\nequipoMayorPromedio");
		//System.out.println(cmpt.equipoMayorPromedio());
		/*
		System.out.println("\nlistPartPorResultado");
		Map<ResultadoQuiniela, List<Partido>> m = cmpt.listPartPorResultado();
		for(ResultadoQuiniela r:m.keySet()) {
			System.out.println(r+" --> "+m.get(r));
		}*/
		/*
		System.out.println("\nsetPartPorResultado");
		Map<ResultadoQuiniela, Set<Partido>> m2 = cmpt.setPartPorResultado();
		for(ResultadoQuiniela r:m2.keySet()) {
			System.out.println(r+" --> "+m2.get(r));
		}*/
		
		printMap(cmpt.ssetPartPorResultado());
		
		printMap(cmpt.ssGolesMarcadosPorDia());
	}
	
	public static <k,v> void printMap(Map<k,v> map) {
		for(Map.Entry<k,v> entry: map.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
	}
}
