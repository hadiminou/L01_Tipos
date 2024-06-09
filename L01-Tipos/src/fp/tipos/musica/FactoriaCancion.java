package fp.tipos.musica;


import fp.tipos.musica.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaCancion {
	public static Cancion parseCancion(String str) {  // static depende de estado de cada objeto. es metodo de utilidad
		//devuelve un objeto de tipo cancion a partir de los datos que esten en el string
		// que se pasa como parametro
		//formato :Wanna Be Startin' Something, Michael Jackson, 6:03, 1982/11/30, Pop
		String[] trozos = str.split(",");
		Checkers.check("formato incorrecto", trozos.length == 5);
		
		String titulo = trozos[0];
		String artista = trozos[1].trim();  //.strip()
		//Duration duracion =  Duration.parse(trozos[2].trim());
		Duration duracion =  parseDuration(trozos[2].trim());
		LocalDate fechaLanzamiento = LocalDate.parse(trozos[3].trim(),
				DateTimeFormatter.ofPattern("y/M/d"));
		Genero genero = Genero.valueOf(trozos[4].trim().toUpperCase());
		
		Cancion c = new Cancion(titulo, artista);
		c.setDuracion(duracion);
		c.setFechaLanzamiento(fechaLanzamiento);
		c.setGenero(genero);
		return c;
	}
	
	private static Duration parseDuration(String str) {
		String[] trozos = str.split(":");
		Long minutos = Long.valueOf(trozos[0]);
		Long segundos = Long.valueOf(trozos[1]);
		return Duration.ofMinutes(minutos).plusSeconds(segundos);
		}

	public static ListaReproduccion leerCanciones(String fichero) {
		//devuelve uan lista de reproduccion con todas las canciones del fichero
		List<Cancion>canciones = Ficheros.leeFichero("Error al intentar leer el fichero", fichero, 
				linea -> parseCancion(linea), true);  // exprecoin lambda linea
		ListaReproduccion l = new ListaReproduccion("mi lista");
		for (Cancion c:canciones) { // formato: for (tipo elemento:lista o conjunto)
			l.incorpora(c);
		}
	return l;
	}
}