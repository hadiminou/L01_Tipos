package fp.tipos.musica.test;
 
import java.time.Duration;

import java.time.LocalDate;
 
import fp.tipos.musica.Cancion;

import fp.tipos.musica.Genero;
 
public class TestCancion {
    public static void main(String[] args) {
        // Crear un objeto Cancion utilizando el constructor que recibe título y artista
        Cancion cancion1 = new Cancion("Bohemian Rhapsody", "Queen");
        Cancion cancion2 = new Cancion("Bohemian Rhapsody", "Queen");
        Cancion cancion3 = new Cancion("Bohemian Rhapsody", "Queen");
 
        // Actualizamos las propiedades duración, fecha y género
        Duration d1 = Duration.ofMinutes(5);
        Duration d2 = d1.plusSeconds(55);
        
        //devolvemos una duracion nueva(d2 como resultado de sumarle 55s a la duracion d1)
        
        //cancion1.setDuracion(Duration.ofMinutes(5).plusSeconds(55));
        cancion1.setDuracion(d2);
        cancion1.setFechaLanzamiento(LocalDate.of(1975, 10, 31));
        cancion1.setGenero(Genero.ROCK);
        
        //Negative duration
        //Duration d3 = Duration.ofMinutes(-5);
        //cancion3.setDuracion(d3);
 
        // Mostrar el objeto en pantalla
        System.out.println("Canción 1:");
        System.out.println(cancion1);
        System.out.println("Formato corto: " + cancion1.getFormatoCorto());
        
        System.out.println("compareTo: "+cancion1.compareTo(cancion2));
        System.out.println("equals: "+cancion1.equals(cancion2));
        //System.out.println();
 
        /*
         * CREA DOS CANCIONES MÁS
         */
        
    }
}