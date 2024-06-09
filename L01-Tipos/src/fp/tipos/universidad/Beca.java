package fp.tipos.universidad;

import java.util.Objects;

import fp.utiles.Checkers;

public class Beca implements Comparable<Beca>{
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;
	
	public Double getCuantiaMensual() {
		return this.cuantiaTotal/this.duracion;
	}
	
	public Beca(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		Checkers.check("el código está formado por tres letras y cuatro dígitos",
				codigo.length() == 7 && Character.isAlphabetic(codigo.charAt(0)) 
				&& Character.isAlphabetic(codigo.charAt(1))
				&& Character.isAlphabetic(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3))
				&& Character.isDigit(codigo.charAt(4))
				&& Character.isDigit(codigo.charAt(5))
				&& Character.isDigit(codigo.charAt(6)));
		Checkers.check("el valor de la cuantía total debe ser mayor o igual que la cuantía mínima"
				+ " (1500 euros)", cuantiaTotal >= 1500d);
		Checkers.check("el valor de la duración debe ser mayor o igual que la duración mínima(1mes)"
				, duracion >= 1);
		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;
	}
	
	//permite crear becas de las que no se conoce aún su cuantía ni su duración. 
	//Por defecto la cuantía será de 1500,0 € (definirlo como constante), 
	//y tendrá una duración de un mes.
	public Beca(String codigo, TipoBeca tipo) {
		Checkers.check("el código está formado por tres letras y cuatro dígitos",
				codigo.length() == 7 && Character.isAlphabetic(codigo.charAt(0)) 
				&& Character.isAlphabetic(codigo.charAt(1))
				&& Character.isAlphabetic(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3))
				&& Character.isDigit(codigo.charAt(4))
				&& Character.isDigit(codigo.charAt(5))
				&& Character.isDigit(codigo.charAt(6)));
		Checkers.check("el valor de la cuantía total debe ser mayor o igual que la cuantía mínima"
				+ " (1500 euros)", cuantiaTotal >= 1500d);
		Checkers.check("el valor de la duración debe ser mayor o igual que la duración mínima(1mes)"
				, duracion >= 1);
		this.codigo = codigo;
		this.cuantiaTotal = 1500d;
		this.duracion = 1;
		this.tipo = tipo;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		Checkers.check("el valor de la duración debe ser mayor o igual que la duración mínima(1mes)"
				, duracion >= 1);
		this.duracion = duracion;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Beca [codigo=" + codigo + ", cuantiaTotal=" + cuantiaTotal + ", duracion=" + duracion + ", tipo=" + tipo
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, cuantiaTotal, duracion, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Beca))
			return false;
		Beca other = (Beca) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public int compareTo(Beca b) {
		int res = this.codigo.compareTo(b.codigo);
		return res;
	}
}
