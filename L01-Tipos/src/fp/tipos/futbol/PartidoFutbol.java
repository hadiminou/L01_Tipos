package fp.tipos.futbol;

import java.time.LocalDateTime;

import java.util.Objects;

public class PartidoFutbol {
	private LocalDateTime fecha;
	private String equipoLocal;
	private String equipoVisitante;
	private Integer golesLocal;
	private Integer golesVisitante;
	//private String cadenaConFormato;
	
	public ResultadoQuiniela getResultado(){
		if(this.golesLocal>this.golesVisitante) {
			return ResultadoQuiniela.UNO;
		}
		else if(this.golesLocal==this.golesVisitante) {
			return ResultadoQuiniela.EQUIS;
		}
		else {
			return ResultadoQuiniela.DOS;
		}
	}
	
	public String getCadenaConFormato(){
		return this.fecha.toLocalDate()+ "->" + this.equipoLocal + " vs " + this.equipoVisitante +
				": " + this.golesLocal + "-" + this.golesVisitante + "(" + getResultado() + ")";
	}
	
	public PartidoFutbol(LocalDateTime fecha, String equipoLocal, String equipoVisitante, 
			Integer golesLocal, Integer golesVisitante) {
		this.fecha = fecha;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public int hashCode() {
		return Objects.hash(equipoLocal, equipoVisitante, fecha, golesLocal, golesVisitante);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PartidoFutbol))
			return false;
		PartidoFutbol other = (PartidoFutbol) obj;
		return Objects.equals(equipoLocal, other.equipoLocal) && Objects.equals(equipoVisitante, other.equipoVisitante)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(golesLocal, other.golesLocal)
				&& Objects.equals(golesVisitante, other.golesVisitante);
	}

	public String toString() {
		return "PartidoFutbol [fecha=" + fecha + ", equipoLocal=" + equipoLocal +
				", equipoVisitante=" + equipoVisitante + ", golesLocal=" + golesLocal +
				", golesVisitante=" + golesVisitante + "]";
	}
}
