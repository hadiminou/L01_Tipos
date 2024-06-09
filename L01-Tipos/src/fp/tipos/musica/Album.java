package fp.tipos.musica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public record Album(String nombre, String id, Integer popularidad, Integer anoPublicacion, 
		TipoDeAlbum tipo, List<Cancion> canciones, List <String> imagenes) {
	
	public Album(String nombre, String id, Integer popularidad, Integer anoPublicacion, 
		TipoDeAlbum tipo) {
		this (nombre, id, popularidad, anoPublicacion, tipo,new ArrayList<Cancion>() ,
				new ArrayList<String>());
	}
	
	public Album{
		Checkers.check("el id debe tener 22 caracteres", id.length()==22);
		Checkers.check("la popularidad debe estar comprendida entre 0 y 100, ambos inclusive",
				popularidad >= 0 && popularidad <= 100);
		Checkers.check("la url de todas las imágenes del álbum debe comenzar por “http”",
				allStartWith("http"));
	}
	
	public Boolean allStartWith(String str) {
		Boolean res = true;
		for (String s:imagenes) {
			if(!s.startsWith(str)) {
				res = false;
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return "Album [nombre=" + nombre + ", id=" + id + ", popularidad=" + popularidad + ", anoPublicacion="
				+ anoPublicacion + ", tipo=" + tipo + "numCanciones=" + canciones.size() + 
				"numImagenes=" + imagenes.size() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Album))
			return false;
		Album other = (Album) obj;
		return Objects.equals(id, other.id);
	}
	
	//añade la canción al final del álbum, si la canción no está ya en el álbum. Si está no hace nada.
	public void incorporaCancion(Cancion c) {
		if (! canciones.contains(c)) {
			canciones.add(c);
		}
	}
	
	//añade la canción en la posición dada 
	//como parámetro, si la canción no está ya en el álbum. La posición debe estar en el intervalo
 	//[0, número de canciones del álbum], si no lo está, se eleva IllegalArgumentException.
	public void incorporaCancion(Cancion c, int posicion) {
		if (posicion < 0 || posicion > canciones.size()) {
			throw new IllegalArgumentException("La posición debe estar en el intervalo"
					+ "[0, número de canciones del álbum]");
		}
		if (posicion >= 0 && posicion <= canciones.size() && !canciones.contains(c)) {
			canciones.add(posicion, c);
		}
	}
	
	//elimina la canción del álbum. Si la canción no está en el álbum, no hace nada
	public void eliminaCancion(Cancion c) {
		if (canciones.contains(c)) {
			canciones.remove(c);
		}
	}
	
	//elimina la canción del álbum situada en la posición dada 
	//como parámetro. La posición debe estar en el intervalo [0, número de canciones del álbum),
	//si no lo está, se eleva IllegalArgumentException
	public void eliminaCancion(int posicion) {
		if (posicion < 0 || posicion > canciones.size()) {
			throw new IllegalArgumentException("La posición debe estar en el intervalo"
					+ "[0, número de canciones del álbum]");
		}
		if (posicion >= 0 && posicion <= canciones.size()) {
			canciones.remove(posicion);
		}
	}
	
	//incorpora la ruta de una imagen a las imágenes del álbum
	public void incorporaImagen(String ruta) {
		imagenes.add(ruta);
	}
	
	//elimina la imagen situada en la posición dada como 
	//parámetro. La posición debe estar en el interavalo [0, numero de imágenes), si no lo está,
	//se eleva IllegalArgumentException
	public void eliminaImagen(int posicion) {
		if(posicion < 0 || posicion > imagenes.size()) {
			throw new IllegalArgumentException("La posición debe estar en el interavalo"
					+ " [0, numero de imágenes)");
		}
		if (posicion >= 0 && posicion <= imagenes.size()) {
			imagenes.remove(posicion);
		}
	}
}
