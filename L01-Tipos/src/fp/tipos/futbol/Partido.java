package fp.tipos.futbol;

import java.time.LocalDateTime;
import java.util.Objects;

import fp.utiles.Checkers;

public record Partido(LocalDateTime fecha, String equipoLocal, String equipoVisitante, 
		Integer golesLocal, Integer golesVisitante)implements Comparable<Partido> {
	
	public ResultadoQuiniela getResultado() {
		//UNO, si el equipo local tiene más goles que el equipo visitante; será EQUIS,
		//si los dos equipos han marcado el mismo número de goles, y DOS, si el equipo visitante
		//ha marcado más goles que el local
		ResultadoQuiniela res;
		if (golesLocal > golesVisitante) {
			res = ResultadoQuiniela.UNO;
		}
		else if (golesLocal.equals(golesVisitante)) {
			res = ResultadoQuiniela.EQUIS;
		}
		else {
			res = ResultadoQuiniela.DOS;
		}
		return res;
	}
	
	public String cadenaConFormato() {
		return fecha + "->" + equipoLocal + " vs " + equipoVisitante +
				": " + golesLocal + "-" + golesVisitante + "(" + getResultado() + ")";
	}
	
	public Partido{
		Checkers.check("El nombre de los equipos no puede ser cadena vacia",
				equipoLocal != "" && equipoVisitante != "");
		//Checkers.check("los goles de ambos equipos no pueden tener un valor negativo", 
			//	golesLocal > 0 && golesVisitante > 0);
	}

	@Override
	public String toString() {
		return "PartidoFutbol2 [fecha=" + fecha + ", equipoLocal=" + equipoLocal + ", equipoVisitante="
				+ equipoVisitante + ", golesLocal=" + golesLocal + ", golesVisitante=" + golesVisitante + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(equipoLocal, equipoVisitante, fecha, golesLocal, golesVisitante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Partido))
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(equipoLocal, other.equipoLocal) && Objects.equals(equipoVisitante, other.equipoVisitante)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(golesLocal, other.golesLocal)
				&& Objects.equals(golesVisitante, other.golesVisitante);
	}

	@Override
	public int compareTo(Partido p) {
		// por fecha, si coinciden por nombre del equipo local, y si coinciden,
		//por nombre del equipo visitante.
		if (this.equals(p)) {
			return 0;
		}
		else {
			int res = this.fecha.compareTo(p.fecha);
			if (res == 0) {
				res = this.equipoLocal.compareTo(p.equipoLocal);
				if (res == 0) {
					res = this.equipoVisitante.compareTo(p.equipoVisitante);
					if (res == 0) {
						res = 1; // elegimos un valor al azar que no sea cero
					}
				}
			}
		return res;
		}
	}
	

}
