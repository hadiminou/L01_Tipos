package fp.tipos.musica;

import java.time.Duration;


import java.time.LocalDate;

import java.util.Objects;

import fp.utiles.Checkers;


public class Cancion implements Comparable<Cancion> {
	/*
	titulo, de tipo String, consultable y modificable.
	artista, de tipo String, consultable y modificable. Representa al intérprete de la canción.
	duracion, de tipo Duration, consultable y modificable.
	fechaLanzamiento, de tipo LocalDate, consultable y modificable.
	genero, de tipo Genero, consultable y modificable. Puede tomar los valores: POP, ROCK, FOLK.
	formatoCorto, de tipo String, consultable. Cadena que representa una canción con el
	siguiente formato: el título de la canción, seguido del artista entre paréntesis y 
	la duración, por ejemplo, “Whole Lotta Love (Led Zeppelin) 3:20
*/
	
	// propiedades
	private String titulo;
	private String artista;
	private Duration duracion;
	private LocalDate fechaLanzamiento;
	private Genero genero;
	//private String formatoCorto;
	
	public String getFormatoCorto() {
		return this.titulo + " (" + this.artista + ") " + this.duracion.toMinutesPart(); // creo que no es completo
	}
	
	/* constructor: recibe como parametro el titulo e el artista y crea una cancion con duracion
	   de 0 segundos y el resto de atributos nulos */
	
	public Cancion(String titulo, String artista) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracion = Duration.ZERO;  // duracion cero
	}
	
	/*public Cancion() {
		if (this.duracion.getSeconds()<0) {
			throw new IllegalArgumentException("el valor en segundos de la"
					+ " duración de una canción siempre es mayor o igual que cero.");
		}
	}*/

	// metodos consultores y modificadores
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		/*if (duracion.isNegative()) {
			throw new IllegalArgumentException("El valor en segundos de la"
					+ " duración de una canción siempre es mayor o igual que cero.");
		}*/
		Checkers.checkNoNull("El valor en segundos de la\"\r\n"
		 + " duración de una canción siempre es mayor o igual que cero.\"", !duracion.isNegative());
		this.duracion = duracion;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	//metodos de objeto: toString, equals, hashCode
	
	public String toString() {
		return "Cancion [titulo=" + titulo + ", artista=" + artista + ", duracion=" + duracion + 
				", fechaLanzamiento=" + fechaLanzamiento + ", genero=" + genero + "]";
	}

	public int hashCode() {
		return Objects.hash(artista, duracion, fechaLanzamiento, genero, titulo);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(artista, other.artista) && Objects.equals(duracion, other.duracion)
				&& Objects.equals(fechaLanzamiento, other.fechaLanzamiento) && genero == other.genero
				&& Objects.equals(titulo, other.titulo);
	}
	/*
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(artista, other.artista) && Objects.equals(titulo, other.titulo);
	}*/


	public int compareTo(Cancion c) {
		// las canciones se ordenan por artista y titulo 
		if (this.equals(c)) {  //hacemos esto para que comoareTo sea coherente con equals,
			//es decir devuelva cero solamente cuando dos canciones sean iguales
			return 0;
		}
		else {
			int res = this.artista.compareTo(c.artista);
			if(res==0) {
				res = this.titulo.compareTo(c.titulo);
				if(res == 0) { // en el caso que coincidan artista y titulo, devolvemos 
					//un numero entero al azar
					res = 1; 
				}
			}
			return res;		
		}	
	}
	
	/*
	public int compareTo(Cancion c) {
		// TODO Auto-generated method stub
		
		int res = this.artista.compareTo(c.artista);
		if(res==0) {
			res = this.titulo.compareTo(c.titulo);
			// a partir de aqui comparamos el resto de propiedades al azar, para mantener 
			 // la compatibilidad con equals
			if (res == 0){
				res = this.fechaLanzamiento.compareTo(c.fechaLanzamiento);
				if(res == 0) {
					res = this.genero.compareTo(c.genero;
					if (res == 0){
						res = this.duracion.compareTo(c.duracion);
					
					} 
				}
			}
		}
		return res;	
	}	
	*/
}
