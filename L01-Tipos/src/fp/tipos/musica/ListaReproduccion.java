package fp.tipos.musica;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;


public class ListaReproduccion {
	private String nombre;
	private List<Cancion> canciones;
	
	public Integer getNumCanciones() {
		return canciones.size();
		//return this.canciones.size();  aquie es mismo
	}
	
	public ListaReproduccion(String nombre) {
		this.nombre = nombre;
		this.canciones = new ArrayList<Cancion>(); // crea una lista vacia
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return new ArrayList<>(this.canciones);
	}
	
	public String toString() {
		return this.nombre + " (" + this.getNumCanciones()+")";
	}

	public int hashCode() {
		return Objects.hash(canciones, nombre);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ListaReproduccion))
			return false;
		ListaReproduccion other = (ListaReproduccion) obj;
		return Objects.equals(canciones, other.canciones) && Objects.equals(nombre, other.nombre);
	}
	
	//operaciones
	public void aleatoriza() {
		Collections.shuffle(this.canciones);
	}
	
	public void incorpora(Cancion c) {
	this.canciones.add(c);
	}
	
	public void incorpora(List<Cancion> canciones) {
	this.canciones.addAll(canciones);
	}
	
	public void eliminaPrimera(Cancion c) {
		if(!this.canciones.contains(c)) {
			throw new IllegalArgumentException("La cancion no existe en la lista");
		}
		this.canciones.remove(c);
	}
	
	public void eliminaUltima(Cancion c) {
		if(!this.canciones.contains(c)) {
			throw new IllegalArgumentException("La cancion no existe en la lista");
		}
		int idx = this.canciones.lastIndexOf(c);
		this.canciones.remove(idx);
	}
	
	public void eliminaTrozo(int primeraPosicion, int ultimaPosicion) {
		if(!(primeraPosicion>=0 && ultimaPosicion<this.getNumCanciones()&& 
				primeraPosicion<=ultimaPosicion)) {
			throw new IllegalArgumentException("Indices incorrectas");
		}
		this.canciones.subList(primeraPosicion, ultimaPosicion).clear();
	}
	
	public Integer getNumeroCancionesComienzanPorCadena(String cadena) {
		int contador = 0;
		for (Cancion c:canciones) {
			if(c.getTitulo().startsWith(cadena)) {
				contador++;
			}
		}
		return contador;
	}
	
	public Duration getDuracionTotal() {
		Duration total = Duration.ZERO; 
		for(Cancion c:canciones) {
			total = total.plus(c.getDuracion());
		}
		return total;
	}
	
	public Duration getDuracionMedia() {
		Long segTotal = getDuracionTotal().toSeconds();
		//Double mediaSeg = segTotal*1.0 / getNumCanciones();
		Long mediaSeg = segTotal / getNumCanciones();
		return Duration.ofSeconds(mediaSeg);
		//return getDuracionTotal().dividedBy(getNumCanciones());  hacer en una sola linea
	}
	
	public List getCancionesGenero(Genero genero) {
		// obtiene una lista con las canciones dado como parametro 
		List<Cancion>res = new ArrayList<Cancion>();
		for (Cancion c: canciones) {
			if (c.getGenero().equals(genero)) {
				res.add(c);
			}
		}
		return res;
	}
	
	public Boolean existeCancionConPalabraEnTitulo(String palabra) {
		boolean res = false;
		for(Cancion c: canciones) {
			if (c.getTitulo().contains(palabra)) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Boolean todasSuperioresA(Integer minutos) {
		boolean res = false;
		for (Cancion c : canciones) {
			if (c.getDuracion().toMinutes()>=minutos) {
				res = true;
			}
		}
		return res;
	}
	
	public Cancion getCancionMayorDuracion() {
		Cancion cMax = null;
		for (Cancion c: canciones) {
			if (cMax == null || c.getDuracion().toSeconds()>cMax.getDuracion().toSeconds()) {
				cMax = c;
			}
		}
		return cMax;
		
	}
	
	public Map<Integer, List<Cancion>> agrupaCancionesPorAno(){
		//Map<Integer, List<Cancion>> res = new HashMap<Integer, List<Cancion>>();
		SortedMap<Integer, List<Cancion>> res = new TreeMap<Integer, List<Cancion>>();
		
		for (Cancion c: canciones) {
			//extraigo la clave
			Integer ano = c.getFechaLanzamiento().getYear();
			
			//preguntamos al diccionario si no esta la clave
			if (!res.containsKey(ano)) {
				//si no existia, la agrego al diccionario
				res.put(ano, new ArrayList<Cancion>());
			}
			//en cualquiuer caso agregamos la cancion c a lista asociada a la clave ano
			res.get(ano).add(c);
		}
		return res;
	}
	
	public Map<Integer, List<Cancion>> agrupaCancionesArtistaPorPalabras(String artista){
		Map<Integer, List<Cancion>> res = new HashMap<Integer, List<Cancion>>();
		
		for (Cancion c: canciones) {
			if(c.getArtista().equals(artista)) {
				Integer numPalabras = calculaNumPalabras(c.getTitulo());
				if(!res.containsKey(numPalabras)) {
					res.put(numPalabras, new ArrayList<Cancion>());
				}
				res.get(numPalabras).add(c);
			}
		}
		return res;
	}

	private Integer calculaNumPalabras(String titulo) {
		String[] cachos = titulo.split(" ");
		return cachos.length;
	}
	
	public Map<String, Long> getNumeroCancionesPorArtista(){
		//crea un Map que relaciona cada artista con el número de canciones del artista 
		//que hay en la lista de reproducción
		Map<String, Long>res = new HashMap<String, Long>();
		for (Cancion c: canciones) {
			String artista = c.getArtista();
			if (!res.containsKey(artista)) {
				res.put(artista, 0L);
			}
			Long numCanciones = res.get(artista);
			numCanciones+=1;
			res.put(artista, numCanciones);
		}
		return res;
	}

	public Map<Genero, Duration> getDuracionCancionesPorGenero(){
		//crea un Map que relaciona cada género con la duración total de las canciones de ese género
		Map<Genero, Duration>res = new HashMap<Genero, Duration>();
		for (Cancion c: canciones) {
			Genero g = c.getGenero();
			if (!res.containsKey(g)) {
				res.put(g, Duration.ZERO);
			}
			Duration duracion = res.get(g);
			res.put(g, duracion.plus(c.getDuracion()));
		}
		return res;
	}
	
	
}
