package fp.tipos.musica.test;
import fp.tipos.musica.*;
import java.time.Duration;


public class TestListaReproduccion {
    public static void main(String[] args) {
        // Crear un objeto ListaReproduccion usando el constructor
        ListaReproduccion lista1 = new ListaReproduccion("Mi lista");

        // Crear algunas canciones
        Cancion cancion1 = new Cancion("Cancion 1", "Artista 1");
        Cancion cancion2 = new Cancion("Cancion 2", "Artista 2");
        Cancion cancion3 = new Cancion("Cancion 3", "Artista 3");
        
        // Actualizamos las propiedades duración, fecha y género
        Duration d1 = Duration.ofMinutes(125);
        Duration d2 = d1.plusSeconds(55);
        Duration d3 = Duration.ofMinutes(210);
        cancion1.setDuracion(Duration.ofMinutes(145).plusSeconds(55));
        cancion2.setDuracion(d2);
        cancion3.setDuracion(d3);

        // Incorporar las canciones a la lista
        lista1.incorpora(cancion1);
        lista1.incorpora(cancion2);
        lista1.incorpora(cancion3);

        // Mostrar el objeto ListaReproduccion
        System.out.println(lista1.toString());

        // Modificar los atributos que son modificables
        lista1.setNombre("Mi lista actualizada");

        // Mostrar el objeto ListaReproduccion después de modificar los atributos
        System.out.println(lista1.toString());

        // Realizar pruebas con las operaciones de la lista
        lista1.aleatoriza();
        System.out.println("Lista aleatorizada: " + lista1.toString());

        lista1.eliminaPrimera(cancion1);
        System.out.println("Lista después de eliminar la primera ocurrencia de la canción 1: " 
        + lista1.toString());

        lista1.eliminaUltima(cancion2);
        System.out.println("Lista después de eliminar la última ocurrencia de la canción 2: " 
        + lista1.toString());

        lista1.eliminaTrozo(0, 1);
        System.out.println("Lista después de eliminar el trozo entre las posiciones 0 y 1: " 
        + lista1.toString());
    }
}
