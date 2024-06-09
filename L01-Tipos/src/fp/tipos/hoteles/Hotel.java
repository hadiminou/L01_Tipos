package fp.tipos.hoteles;

import java.util.Objects;

import fp.utiles.Checkers;

public record Hotel(String nombre, String dirección, String ciudad, String telefono,
                    String cadenaHotelera, String descripción, CategoriaHotelera categoria,
                    TipoAlojamiento tipo, CategoriaPrecio categoríaPrecio, Float puntuacion,
                    Integer numeroComentarios, Boolean admiteMascotas, Boolean estáAdaptado,
                    Coordenada coordenadas) {

    // Constructor C2
    public Hotel(String nombre, String cadenaHotelera, CategoriaHotelera categoria) {
        this(nombre, null, null, null, cadenaHotelera, null, categoria, null, CategoriaPrecio.MEDIA,
                0.0f, 0, false, false, null);
    }

    // Constructor C3
    public Hotel(String nombre, String cadenaHotelera, CategoriaHotelera categoria,
                 CategoriaPrecio categoríaPrecio) {
        this(nombre, null, null, null, cadenaHotelera, null, categoria, null, categoríaPrecio,
                0.0f, 0, false, false, null);
    }
    
    public Hotel{
    	Checkers.check("la puntuación es nula o un número mayor o igual que cero",
    			puntuacion == null || puntuacion >= 0);
    	Checkers.check("el número de comentarios es nulo o un número mayor o igual que cero",
    			numeroComentarios == null || numeroComentarios >= 0);
    }

    public String getCadenaConFormato() {
        //return nombre + " (" + "*".repeat(categoria.estrellas()) + ")";
        String res = "N/A";
        if(categoria == CategoriaHotelera.UNA) {
        res = "*";
        }
        else if(categoria == CategoriaHotelera.DOS) {
        res = "**";
        }
        else if(categoria == CategoriaHotelera.TRES) {
        res = "***";
        }
        else if(categoria == CategoriaHotelera.CUATRO) {
        res = "****";
        }
        else if(categoria == CategoriaHotelera.CINCO) {
        res = "*****";
        }
        return nombre +"("+ res +")";
    }

    @Override
    public String toString() {
        return "Hotel [nombre=" + nombre + ", dirección=" + dirección + ", ciudad=" + ciudad + ", telefono=" + telefono
                + ", cadenaHotelera=" + cadenaHotelera + ", descripción=" + descripción + ", categoría=" + categoria
                + ", tipo=" + tipo + ", categoríaPrecio=" + categoríaPrecio + ", puntuación=" + puntuacion
                + ", númeroComentarios=" + numeroComentarios + ", admiteMascotas=" + admiteMascotas + ", estáAdaptado="
                + estáAdaptado + ", coordenadas=" + coordenadas + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(admiteMascotas, cadenaHotelera, categoria, categoríaPrecio, ciudad, coordenadas, descripción,
                dirección, estáAdaptado, nombre, numeroComentarios, puntuacion, telefono, tipo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Hotel))
            return false;
        Hotel other = (Hotel) obj;
        return admiteMascotas == other.admiteMascotas && Objects.equals(cadenaHotelera, other.cadenaHotelera)
                && categoria == other.categoria && categoríaPrecio == other.categoríaPrecio
                && Objects.equals(ciudad, other.ciudad) && Objects.equals(coordenadas, other.coordenadas)
                && Objects.equals(descripción, other.descripción) && Objects.equals(dirección, other.dirección)
                && estáAdaptado == other.estáAdaptado && Objects.equals(nombre, other.nombre)
                && numeroComentarios == other.numeroComentarios
                && Float.floatToIntBits(puntuacion) == Float.floatToIntBits(other.puntuacion)
                && Objects.equals(telefono, other.telefono) && tipo == other.tipo;
    }
}
