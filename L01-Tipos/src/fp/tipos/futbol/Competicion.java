package fp.tipos.futbol;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public record Competicion(String nombre, Integer ano, List<Partido>partidos) {
	
	public Competicion(String nombre, Integer ano) {
		this(nombre, ano, new ArrayList<Partido>());
	}
	
	public Competicion {
		Checkers.checkNoNull(nombre, ano);
	}
	
	//otras operaciones
	public void incorporaPartido(Partido p) {
		if(!partidos.contains(p)) {
			partidos.add(p);
		}
	}
	
	public void incorporaPartidos(List<Partido> partidos) {
		this.partidos.addAll(partidos);
	}
	
	//********** Tratamientos secuenciales ************
	
	public Integer getVictoriasVisitantes() {
		Integer res = 0;
		for (Partido p : partidos) {
			if (p.getResultado() == ResultadoQuiniela.DOS) {
				res++;
			}
		}
		return res;
	}
	
	//Arreglar
	public Integer getTotalGolesMarcados(String equipo) {
		Integer res = 0;
		for (Partido p : partidos) {
			if(p.equipoLocal().equals(equipo)) {
				res += p.golesLocal();
			}
			if (p.equipoVisitante().equals(equipo)) {
				res += p.golesVisitante();
			}
		}
		return res;
	}
	
	public Set<String> getEquipos(){
		HashSet<String> res = new HashSet<String>();
		for(Partido p : partidos) {
			res.add(p.equipoLocal());
			res.add(p.equipoVisitante());
		}
		return res;
	}
	
	public Partido getPartidoMasGoles() {
		return Collections.max(partidos, Comparator.comparing(partido->partido.golesLocal()
				+partido.golesVisitante()));
	}
	
	 /*************************** Operaciones de max./min./ordenac. ********************************/

	 public SortedSet<Partido> obtenPartidosOrdenadosPorGoles(){
	// Devuelve un conjunto de partidos ordenado de menor a mayor número de goles marcadados

		 Comparator <Partido> cmp = Comparator.
			comparing((Partido p)-> p.golesLocal()+p.golesVisitante())
			.thenComparing(Comparator.naturalOrder()); // thenComparing((PartidoFutbol2 p) -> p.hashCode());
		 SortedSet<Partido> ss = new TreeSet<Partido>(cmp);
		 ss.addAll(partidos);
		 return ss;
	}
	 
	 public SortedSet<Partido> obtenPartidosOrdenadosPorTamanoNombreEquipos(){
		 Comparator <Partido> cmp = Comparator.comparing((Partido p) -> 
		 p.equipoLocal().length()+ p.equipoVisitante().length())
				 .thenComparing(Comparator.naturalOrder());
		 SortedSet<Partido> ss = new TreeSet<Partido>(cmp);
		 ss.addAll(partidos);
	 return ss;
	 }

	 public SortedMap<Integer, SortedSet<Partido>> agrupaPartidosPorGolesLocal(){
	/*
	 * Devuelve un map, cuyas claves son el número de goles local (de mayor a menor).
	 * Los valores son conjuntos de partidos ordenados por resultado
	 */
		 Comparator<Integer> cmpk = Comparator.reverseOrder();
		 SortedMap<Integer, SortedSet<Partido>> sm =
			new TreeMap<Integer, SortedSet<Partido>>(cmpk);
		 Comparator<Partido> cmp = Comparator.comparing((Partido p)-> p.getResultado())
			.thenComparing(Comparator.naturalOrder());
		 for(Partido p:partidos) {
			 if(!sm.containsKey(p.golesLocal())) {
				 sm.put(p.golesLocal(), new TreeSet<Partido>(cmp));
			 }
			 sm.get(p.golesLocal()).add(p);
		//else {
			//SortedSet<PartidoFutbol2> aux = sm.get(p.golesLocal());
			//aux.add(p);
			//sm.put(p.golesLocal(), aux);
		//}
		 }
		 return sm;
	 }

	 public Partido obtenPartidoEmpiezaAntes(Integer n) {
	/*
	 * Devuelve el partido que empieza antes de entre los que el equipo local ha marcado n goles
	 */
		 SortedSet<Partido> filtrado = agrupaPartidosPorGolesLocal().get(n);
		 Comparator <Partido>cmp = Comparator.comparing((Partido p)-> p.fecha());
	 return Collections.min(filtrado, cmp);
	 }
	 
	 public String obtenEquipoQueHaMarcadoMasGoles() {
		 Map<String, Integer> m = new HashMap<String, Integer>();
		 for(Partido p: partidos) {
			 String k1 = p.equipoLocal();
			 String k2 = p.equipoVisitante();
			 if(!m.containsKey(k1)) {
				 m.put(k1, 0);
			 }
			 if(!m.containsKey(k2)) {
				 m.put(k2, 0);
			 }
			 m.put(k1, m.get(k1)+p.golesLocal());
			 m.put(k2, m.get(k2)+p.golesVisitante());
		 }
		 return Collections.max(m.keySet(), Comparator.comparing((String equipo)-> m.get(equipo)));
	 }
	 
	 /*********************** Operaciones intermedias con streams********************************/
		/*
		 * filter sorted map distinct
		 * 
		 */
	 
	 public <T> void printStream(Stream<T> stream) {
			System.out.println(stream.toList());
		}

		// Filtrar partidos con más de 2 goles de diferencia
	 public Stream<Partido> filtrarPorDiferenciaGoles() {
			return this.partidos().stream().filter(p-> Math.abs(p.golesLocal()-p.golesVisitante()) > 2);
		}

		// Ordenar partidos por fecha
	 public Stream<Partido> ordenarPorFecha() {
			//sintaxis referencia a metodo Tipo::metodo 
			return this.partidos().stream().sorted(Comparator.comparing(Partido::fecha));
		}

		// Obtener días jugados sin repetición
		// Ordena el stream anterior por orden natural   sorted()
		// ahora ordenalo por orden alfabetico de los dias
		// ahora ordenalo por orden alfabetico de los dias inverso
	 public Stream<DayOfWeek> obtenerDiasJugados() {
			return this.partidos().stream().map(p-> p.fecha().getDayOfWeek()).distinct()
					.sorted(Comparator.comparing(DayOfWeek::toString).reversed());
		}

		/******************** Ejercicios para el alumno *****************/

		// Filtrar partidos por equipo. Puede ser local o visitante
	 public Stream<Partido> filtrarPorEquipo(String equipo) {
			return this.partidos().stream().filter(p -> p.equipoLocal().equals(equipo) 
					|| p.equipoVisitante().equals(equipo)); 
		}

		// Filtrar partidos por resultado
	 public Stream<Partido> filtrarPorResultado(ResultadoQuiniela resultado) {
			return this.partidos().stream().filter(p -> p.getResultado().equals(resultado));
		}

		// Obtener equipos que han jugado como local sin repetir
		// ordenarlos por orden natural
		// ordenarlos por el tamano de cada nombre de equipo (mayor a menor)
	 public Stream<String> obtenerEquipoLocalUnicos() {
			//return this.partidos().stream().map(p -> p.equipoLocal()).distinct(); 
			//las dos formas son correctas
			return this.partidos().stream().map(Partido :: equipoLocal).distinct()
					.sorted(Comparator.comparing(String::length).reversed());
		}

		// Obtener los equipos que han jugado como local (sin repetir) y ordenarlos alfabéticamente
	 public Stream<String> obtenerYOrdenarEquiposLocal() {
			return this.partidos().stream().map(p->p.equipoLocal()).distinct().sorted();
		}

		// Filtrar partidos por equipo y ordenarlos por fecha. El equipo puede ser local o visitante
	 public Stream<Partido> filtrarYOrdenarPorEquipoYFecha(String equipo) {
			return filtrarPorEquipo(equipo).sorted(Comparator.comparing(Partido::fecha));
		}

		// obtiene un stream de strings con el formato:
		// "equipo local equipo visitante resultado (1X2)"
		//por ejemplo "sevilla betis X
	 public Stream <String> obten1X2(){
			return this.partidos().stream().map(p->p.equipoLocal()+ " " +p.equipoVisitante() + " " +
					obtenResultado(p.getResultado()));
		}
		
	 public String obtenResultado(ResultadoQuiniela r) {
			String res = null;
			if (r.equals(ResultadoQuiniela.UNO)) {
				res = "1";
			}
			else if (r.equals(ResultadoQuiniela.DOS)) {
				res = "2";
			}
			else if (r.equals(ResultadoQuiniela.EQUIS)) {
				res = "X";
			}
			return res;
		}
		
		// otra manera de hacer otenResultado
	 public String transformaRes(ResultadoQuiniela resultado) {
			String resultadoQuinuiela = "";
			switch (resultado) {
			case UNO:
				resultadoQuinuiela = "1";
				break;
			case EQUIS:
				resultadoQuinuiela = "X";
				break;
			case DOS:
				resultadoQuinuiela = "2";
				break;
			}
			return resultadoQuinuiela;
		}
		
		// Implementa un metodo que obtenga un stream con  
		// el numero de goles totales de aquellos partidos cuyo dicho numero de goles totales 
		// sea primo. Disena tambien la cabecera del metodo 

	 public Stream<Integer> obtenGolesPrimo(){
			return this.partidos().stream().map(p->p.golesLocal()+ p.golesVisitante())
					.filter(n->isPrime(n));
		}
		
		//auxiliar function to find prime number
	 private static boolean isPrime(int number) {
	        if (number <= 1) {
	            return false;
	        }
	        for (int i = 2; i <= Math.sqrt(number); i++) {
	            if (number % i == 0) {
	                return false;
	            }
	        }
	        return true;
	    }
		
		/*********************** Operaciones terminales con streams********************************/

		/*

		 * allMatch, anyMatch, count, findAny, findFirst, forEach, max, min

		 * Para streams numéricos mapTo... : average, sum

		 * 

		 */

	 public Boolean todosJugadosEn(Integer ano) {

		/*

		 * Decide si todos los partidos se han jugado el año especificado 

		 */

			return this.partidos().stream().allMatch(p-> ano.equals(p.fecha().getYear())); 
			// o bien : ano == p.fecha().getYear()

			}

	 public Boolean algunoJugadoEnFecha(LocalDate fecha) {

		/*

		 * Mira si hay algún partido que se ha jugado en la fecha indicada

		 */

			return this.partidos().stream().anyMatch(p->p.fecha().toLocalDate().equals(fecha));

			}

	 public Integer numPartidos() {

		/*

		 * número de partidos

		 */

		 return (int) this.partidos().stream().count();

			}

	 public Partido cualquierPartido() {

		/*

		 * Devuelve un partido cualquiera

		 */

			//return this.partidos().stream().findAny().orElseThrow(()->new IllegalStateException());
			//return this.partidos().stream().findAny().orElseThrow(null);
			return this.partidos().parallelStream().findAny().get(); 
			// parallel para que nos da un partido diferente. pero es solo para saber!

			}

	 public Partido primerPartido() {

		/*

		 * Devuelve el primer partido del Stream

		 */

		    return this.partidos().parallelStream().findFirst().orElseThrow(()-> 
		    new IllegalStateException());

			}

	 public void imprimeConFormato() {

		/*

		 * Imprime todos los partidos con formato

		 */
		 this.partidos().stream().forEach(p->System.out.println(p.cadenaConFormato()));
	}

	 public Partido partidoMayorDiferencia() {

		/*

		 * Devuelve el partido con mayor diferencia de goles

		 */
		 //Comparator<PartidoFutbol2> cmp = Comparator.comparing(p->Math.abs(p.golesLocal()-p.golesVisitante));
		 return this.partidos().stream().max(Comparator.comparing
				 (p->Math.abs(p.golesLocal()-p.golesVisitante()))).get();

	}

	 public Integer numGolesMarcados() {

		/*
		 * Devuelve el total de goles marcados en todos los partidos. 
		 * Usar flatMap para devolver un Stream de goles
		 */

		 //return this.partidos().stream().mapToInt(p -> p.golesLocal() + p.golesVisitante()).sum();
		 //return this.partidos().stream().flatMap(p-> Stream.of(p.golesLocal(), p.golesVisitante()))
		//		 .mapToInt(g->g).sum();
		 return this.partidos().stream().
				 flatMapToInt(p->IntStream.of(p.golesLocal(), p.golesVisitante())).
				 sum();
	}

			

			/*********************** A resolver por el alumno **********************************/

	 public Boolean todosDiferenciaEntre(Integer min, Integer max) { 

		/*

		 * Decide si todos los partidos tienen una diferencia de goles comprendida entre min y max,
		 *  ambas incluidas

		 */

		 return this.partidos().stream().allMatch(p-> Math.abs(p.golesLocal()-p.golesVisitante()) >= min
				 && Math.abs(p.golesLocal()-p.golesVisitante()) <= max);

		}

	 public Boolean algunoConGolesTotales(Integer n) {

		/*

		 * Mira si algún partido tiene un total de goles de n

		 */

		 return this.partidos().stream().anyMatch(p->p.golesLocal()+p.golesVisitante() == n);

		}

	 public Integer numFechasDistintas() {

		/*

		 * Devuelve el número de fechas distintas en las que ha habido partido

		 */

		 return (int) this.partidos().stream().map(p->p.fecha().toLocalDate()).distinct().count();
		 //return (int) this.partidos().stream().map(PartidoFutbol2::fecha).distinct().count(); 
		 //aquie tiene problema asi

		}

	public String equipoLocalNgoles(Integer n) {

		/*

		 * Devuelve cualquier equipo local que haya marcado n goles 

		 */

		 return this.partidos().stream().filter(p->p.golesLocal().equals(n))
				 .findAny().get().equipoLocal();
			}

	public void imprimeGolesTotales() {

		/*

		 * Imprime los goles totales de todos los partidos

		 */
			this.partidos().stream().forEach(p->System.out.println(p.golesLocal()+p.golesVisitante()));
			}

	public Partido partidoMasAntiguo() {

		/*

		 * Devuelve el partido más angtiguo

		 */

		 return this.partidos().stream().min(Comparator.comparing(p->p.fecha().toLocalDate())).get();

			}

	public Map<String, Double> promedioGolesPorEquipo1() {  // este no esta correcto!!
		/*
		 * Devuelve el promedio de goles marcados por cada equipo (en forma de diccionario)
		 * Usar flatMap para obtener un Stream de goles
		 */
		Map<String, List<Partido>> m = new HashMap<String, List<Partido>>();
		for(Partido p: partidos) {
			 if(!m.containsKey(p.equipoLocal())) {
				 m.put(p.equipoLocal(), new ArrayList<Partido>());
			 }
			 if(!m.containsKey(p.equipoVisitante())) {
				 m.put(p.equipoVisitante(), new ArrayList<Partido>());
			 }
			 m.get(p.equipoLocal()).add(p);
			 m.get(p.equipoVisitante()).add(p);
		}
		Map <String, Double> res = new HashMap<String, Double>();
		for (String eq:m.keySet()) {
			res.put(eq, m.get(eq).stream().flatMapToInt(p->
			IntStream.of(p.golesLocal(), p.golesVisitante())).average().getAsDouble());
		}
		return res;

		}
	
	public Map<String, Double> promedioGolesPorEquipo() {  // version correcto
		/*
		 * Devuelve el promedio de goles marcados por cada equipo (en forma de diccionario)
		 */
		 Map<String, List<Integer>> aux = new HashMap<>();
		 for(Partido p:partidos) {
			 String eqL = p.equipoLocal();
			 String eqV = p.equipoVisitante();
		 	 if(!aux.containsKey(eqL)) {
		 		aux.put(eqL, new ArrayList<>());
		 	 }
		 	 aux.get(eqL).add(p.golesLocal());
		 	 if(!aux.containsKey(eqV)) {
		 	 	aux.put(eqV, new ArrayList<>());
		     }
		 	 aux.get(eqV).add(p.golesVisitante());
		 }
		 Map<String, Double> res = new HashMap<String, Double>();
		 for(String eq:aux.keySet()) {
			 res.put(eq, aux.get(eq).stream().mapToInt(n->n).average().getAsDouble());
		 }
		 return res;
	}
	
	public String equipoMayorPromedio() {
		// devuelve el equipo con mayor promedio de goles marcados
		return promedioGolesPorEquipo().entrySet().stream()
				.max(Comparator.comparing(par -> par.getValue())).get().getKey();
	}
	
	/*********************************** groupingBy *************************************/
	/*	- Agrupar colecciones (listas, conjuntos, conjuntos ordenados)
	 *  - Agrupar medias
		- Agrupar conteos
		- Agrupar max/min
		- Agrupar y mapear
		- Usar alguno de los ejemplos para mostrar cómo se devuelve un SortedMap
	 * 
	 */
	
	public Map<ResultadoQuiniela, List<Partido>> listPartPorResultado(){
		/*
		 * Lista de partidos por cada resultado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(Partido::getResultado));
	}
	public Map<ResultadoQuiniela, Set<Partido>> setPartPorResultado(){
		/*
		 * Conjunto de partidos por cada resultado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.getResultado(), 
						Collectors.toSet()));
	}
	public Map<ResultadoQuiniela, SortedSet<Partido>> ssetPartPorResultado(){
		/*
		 * Conjunto ordenado de partidos por dada resultado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(Partido::getResultado,
						Collectors.toCollection(TreeSet::new)));
	}
	public Map<ResultadoQuiniela, Double> mediaGolesLocalPorResultado(){
		/*
		 * Promedio de goles del equipo local por resultado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(Partido::getResultado, 
						Collectors.averagingInt(Partido::golesLocal)));
	}
	public Map<ResultadoQuiniela, Integer> numPartidosPorResultado(){
		/*
		 * Núm. de partidos por resultado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(Partido::getResultado, 
						Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
		
	}
	public Map<ResultadoQuiniela,Partido> primerPartidoPorResultado(){
		/*
		 * Primer partido jugado por cada resultado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(Partido::getResultado,
						Collectors.collectingAndThen(Collectors.
								minBy(Comparator.comparing(Partido::fecha)), Optional::get)));
	}
	public Map<ResultadoQuiniela, List<String>> listFormatoPorResultado(){
		/*
		 * Lista de cadenas con formato por cada resultado
		 */
		//return this.partidos().stream().
		//		collect(Collectors.groupingBy(Partido::getResultado,
		//				Collectors.mapping(Partido::cadenaConFormato, Collectors.toList())));
		/*version con sorted map: */
		return this.partidos().stream().
				collect(Collectors.groupingBy(Partido::getResultado, TreeMap::new, 
						Collectors.mapping(Partido::cadenaConFormato, Collectors.toList()))); 
	}
	/*********************** A resolver por el alumno **********************************/
	public Map<DayOfWeek, List<Partido>> listPartPorDia(){
		/*
		 * Lista de partidos por día jugado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(),Collectors.toList()));
	}
	public Map<DayOfWeek, SortedSet<Partido>> ssPartPorDia(){
		/*
		 * Conjunto de partidos por día jugado. Cada conjunto debe estar ordenado por goles totales
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(),
						Collectors.toCollection(()->new TreeSet<>
						(Comparator.comparing(p->p.golesLocal()+p.golesVisitante()))))) ;
	}
	public Map<DayOfWeek, Double> mediaGolesVisitantePorDia(){
		/*
		 * Media de goles del equipo visitante por día jugado
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(),
						Collectors.averagingInt(Partido::golesLocal)));
	}
	public Map<DayOfWeek, Integer> numPartidosPorDia(){
		/*
		 * Núm. de partidos jugados cada día
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(),
						Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
	}
	public Map<DayOfWeek, String> masGoleadorPorDia(){
		/*
		 * Equipo local más goleador por día
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(),
						Collectors.collectingAndThen(Collectors.maxBy
						(Comparator.comparing(Partido::golesLocal)),
							opt-> opt.get().equipoLocal())));
	}
	public Map<DayOfWeek,SortedSet<Integer>> ssGolesMarcadosPorDia(){
		/*
		 * Conjunto de goles marcados por día (goles marcados por los dos equipos). Cada conjunto debe
		 *  estar ordenado de forma natural
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(), 
				Collectors.flatMapping((Partido p)->Stream.of(p.golesLocal(), p.golesVisitante()),
								Collectors.toCollection(TreeSet::new))));
	}
	
	/************************ Bonus ************************************/
	
	public Map<DayOfWeek, Double> mediaGolesPorDia(){
		/*
		 * Media de goles por día. Es posible que hacer un 'flat mapping' sea de utilidad
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy(p->p.fecha().getDayOfWeek(),
				Collectors.flatMapping((Partido p) -> 
				Stream.of(p.golesLocal(), p.golesVisitante()),  //este Stream es con mayuscula!!
				Collectors.averagingInt(g->g))));
	}
	public DayOfWeek diaMasGolesPromedio() {
		/*
		 * Día en el que el promedio de goles ha sido mayor
		 */
		return mediaGolesPorDia().
				entrySet().stream().
				max(Comparator.comparing(par-> par.getValue())).
				get().
				getKey();
	}
	
	public Map<List<Object>, Long> numPartidosPorDiaYgoles(){
		/*
		 * num. de partidos por dia y goles marcados ese dia
		 */
		return this.partidos().stream().
				collect(Collectors.groupingBy((Partido p)->
				List.of(p.fecha().getDayOfWeek(), p.golesLocal()+p.golesVisitante()),
						Collectors.counting()));
	}
	
}
