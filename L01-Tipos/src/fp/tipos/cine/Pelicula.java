package fp.tipos.cine;

import java.time.LocalDate;

import java.util.Objects;

public class Pelicula implements Comparable<Pelicula>{
	private String titulo;
	private LocalDate fechaEstreno;
	private Integer duracion;       // propiedades basicas
	
	public TipoMetraje getTipo() {    // propiedades derivadas
		if(this.duracion<30) {
			return TipoMetraje.CORTOMETRAJE;
		}
		else if(this.duracion>=30 && this.duracion<=60) {
			return TipoMetraje.MEDIOMETRAJE;
		}
		else {
			return TipoMetraje.LARGOMETRAJE;
		}
	}
	
	
	public String getFormatoCorto() {
		String res = this.titulo;
		if(this.fechaEstreno != null) {
			res += " ("+this.fechaEstreno.getYear()+") ";
		}
		return res;
	}


	public Pelicula(String titulo, LocalDate fechaEstreno, Integer duracion) {
		//super();
		if (titulo==null) {
			throw new IllegalArgumentException("el título no puede ser null");
		}
		if (duracion<=0) {
			throw new IllegalArgumentException("la duración debe ser mayor que cero.");
			}
		this.titulo = titulo;
		this.fechaEstreno = fechaEstreno;
		this.duracion = duracion;
		
	}

	public Pelicula(String titulo) {
		if (titulo==null) {
			throw new IllegalArgumentException("el título no puede ser null");
		}
		this.titulo = titulo;
		//this.fechaEstreno = null;
		//this.duracion = null;
	}
	
	
	public LocalDate getFechaEtreno() {
		return fechaEstreno;
	}


	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}


	public Integer getDuracion() {
		return duracion;
	}


	public void setDuracion(Integer duracion) {
		if (duracion<=0) {
			throw new IllegalArgumentException("la duración debe ser mayor que cero.");
			}
		this.duracion = duracion;
	}


	public String getTitulo() {
		return titulo;
	}


	public String toString() {
		return "Pelicula [titulo=" + titulo + ", fechaEstreno=" + fechaEstreno + ", duracion=" + duracion + 
				", tipo=" + getTipo() + "]";
	}


	public int hashCode() {
		return Objects.hash(duracion, fechaEstreno, titulo);
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(fechaEstreno, other.fechaEstreno)
				&& Objects.equals(titulo, other.titulo);
	}

		// criterio de orden natural 
	public int compareTo(Pelicula p) {  // cuando dos objetos son iguales compareTo devuelve cero
		//por fecha de estreno y por título
		int res = this.fechaEstreno.compareTo(p.fechaEstreno);
		if(res==0) {
			res = this.titulo.compareTo(p.titulo);
			// comparamos tambien la duracion paraque compareTo sea compatible con equals 
			if(res == 0) {  
					res = this.duracion.compareTo(p.duracion); 
				}
			}
			return res;		
		}	
}
