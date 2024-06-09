package fp.tipos.aeropuerto;

import java.time.LocalDateTime;
import java.util.Objects;

import fp.utiles.Checkers;

public class Vuelo implements Comparable<Vuelo>{
	private String codigo;
	private String origen;
	private String destino;
	private LocalDateTime fechaSalida;
	private Integer numPlaz;
	private Integer numPasaj;
	
	public Boolean completo(){
		Boolean res = false;
		if (numPasaj == numPlaz) {
			res = true;
		}
		return res;
	}
	
	public String cadenaConFormato() {
		return "(" + this.codigo + ")" + this.origen + "-" + this.destino + this.fechaSalida;
	}
	
	public Vuelo(String codigo, String origen, String destino, LocalDateTime fechaSalida, 
			Integer numPlaz, Integer numPasaj) {
		Checkers.check("la fecha debe ser anterior al día de hoy",
				fechaSalida.isBefore(LocalDateTime.now()));
		Checkers.check("el número de pasajeros debe ser menor o igual que el número de plazas",
				numPasaj <= numPlaz);
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.numPlaz = numPlaz;
		this.numPasaj = numPasaj;
	}
	
	public Vuelo(String codigo, String origen, String destino, LocalDateTime fechaSalida, 
			Integer numPlaz) {
		Checkers.check("la fecha debe ser anterior al día de hoy",
				fechaSalida.isBefore(LocalDateTime.now()));
		Checkers.check("el número de pasajeros debe ser menor o igual que el número de plazas",
				numPasaj <= numPlaz);
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.numPlaz = numPlaz;
		this.numPasaj = 0;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		Checkers.check("la fecha debe ser anterior al día de hoy",
				fechaSalida.isBefore(LocalDateTime.now()));
		this.fechaSalida = fechaSalida;
	}

	public Integer getNumPasaj() {
		return numPasaj;
	}

	public void setNumPasaj(Integer numPasaj) {
		Checkers.check("el número de pasajeros debe ser menor o igual que el número de plazas",
				numPasaj <= numPlaz);
		this.numPasaj = numPasaj;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public Integer getNumPlaz() {
		return numPlaz;
	}

	@Override
	public String toString() {
		return "Vuelo [codigo=" + codigo + ", origen=" + origen + ", destino=" + destino + ", fechaSalida="
				+ fechaSalida + ", numPlaz=" + numPlaz + ", numPasaj=" + numPasaj + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, destino, fechaSalida, numPasaj, numPlaz, origen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vuelo))
			return false;
		Vuelo other = (Vuelo) obj;
		return Objects.equals(codigo, other.codigo) 
				&& Objects.equals(origen, other.origen)
				&& Objects.equals(destino, other.destino)
				&& Objects.equals(fechaSalida, other.fechaSalida);
	}

	@Override
	public int compareTo(Vuelo v) {
		int res = this.codigo.compareTo(v.codigo);
		if (res == 0) {
			res = this.origen.compareTo(v.origen);
			if (res == 0) {
				res = this.destino.compareTo(v.destino);
				if (res == 0) {
					res = this.fechaSalida.compareTo(v.fechaSalida);
				}
			}
		}
		
		return res;
	}
	
	
}
