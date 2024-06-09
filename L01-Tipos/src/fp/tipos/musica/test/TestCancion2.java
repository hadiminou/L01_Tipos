package fp.tipos.musica.test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import fp.tipos.musica.Cancion;
import fp.tipos.musica.ListaReproduccion;
import fp.tipos.musica.FactoriaCancion;
import fp.tipos.musica.Genero;

public class TestCancion2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaReproduccion l = FactoriaCancion.leerCanciones("D:\\Documents\\Uni\\FP\\Java\\"
				+ "eclipse-java-2023-12-R-win32-x86_64\\eclipse\\eclipse-workspace\\Laboratorio\\"
				+ "L01-Tipos\\data\\canciones.csv");
		for(Cancion c:l.getCanciones()) {
			System.out.println(c);
		}
		System.out.println("\nnumero de canciones que empiezan por 'The': " +
		l.getNumeroCancionesComienzanPorCadena("The"));
		System.out.println("\nduracion total: " + l.getDuracionTotal());
		System.out.println("\nduracion media" + l.getDuracionMedia());
		
		System.out.println("\ncanciones con genero pop" + l.getCancionesGenero(Genero.POP));
		
		System.out.println("\nexiste la palabra Nature en algun cancion? "+ 
		l.existeCancionConPalabraEnTitulo("Nature"));
		
		System.out.println("\ntodas canciones tienen duracion mayor que 4?: " + l.todasSuperioresA(4));
		
		System.out.println("\ncancion de mayor duracion: " + l.getCancionMayorDuracion());
		
		/*public static <K, V> void PrintMap(Map<k,v> map) {
			map.entrySet().forEach(entry -> {
				System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
			});
		}*/
		
		System.out.println("\ndiccionario del canciones por ano: " + l.agrupaCancionesPorAno());
		Map<Integer, List<Cancion>> diccionarioPorAno = l.agrupaCancionesPorAno();
		for(Integer ano: diccionarioPorAno.keySet()) {
			System.out.println(ano + " ->" + diccionarioPorAno.get(ano));
		}
		
		System.out.println("\ncanciones agrupados por num palabras de titulo" + 
		l.agrupaCancionesArtistaPorPalabras("Michael Jackson"));
		
		System.out.println("\nnumero de canciones por artista" + l.getNumeroCancionesPorArtista());
	}
	
	
}
