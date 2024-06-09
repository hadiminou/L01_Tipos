package fp.tipos.futbol;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.utiles.Ficheros;


public class FactoriaFutbol {
	
	public static Partido fromString (String partidoStr){
		String[] parts = partidoStr.split(";");
		if (parts.length!=5) {
			throw new IllegalArgumentException("formato de cadena incorrecto. Debe ser: "
					+ "fecha;equipoLocal;equipoVisitante;golesLocal;golesVisitante");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(parts[0], formatter);
		//LocalDateTime fecha = LocalDateTime.parse(parts[0], formatter);
		String equipoLocal = parts[1];
		String equipoVisitante = parts[2];
		Integer golesLocal = Integer.parseInt(parts[3]);
		Integer goleVisitante = Integer.parseInt(parts[4]);
		
		return new Partido(LocalDateTime.of(fecha, LocalTime.now()), 
				equipoLocal , equipoVisitante , golesLocal, goleVisitante);
	}
	
	public static Competicion leeFichero (String fichero, String nombre, Integer ano) {
		Competicion cmpt = new Competicion (nombre, ano);
		List<Partido> partidos = Ficheros.leeFichero("Error", fichero, 
				linea -> fromString(linea), true);
		cmpt.incorporaPartidos(partidos);
		return cmpt;
	}
	
}
		
