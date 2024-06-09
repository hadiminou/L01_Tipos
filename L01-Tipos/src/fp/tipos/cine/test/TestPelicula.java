package fp.tipos.cine.test;

import java.time.LocalDate;

import fp.tipos.cine.Pelicula;

public class TestPelicula {
    public static void main(String[] args) {
        // Crear un objeto Pelicula usando el constructor
        Pelicula pelicula1 = new Pelicula("Titanic", LocalDate.of(1997, 12, 19), 194);

        // Mostrar el objeto Pelicula
        System.out.println(pelicula1.toString());

        // Modificar los atributos que son modificables
        pelicula1.setFechaEstreno(LocalDate.of(1997, 11, 18));
        pelicula1.setDuracion(195);

        // Mostrar el objeto Pelicula después de modificar los atributos
        System.out.println(pelicula1.toString());

        // Crear otro objeto Pelicula para realizar pruebas
        Pelicula pelicula2 = new Pelicula("Avatar", LocalDate.of(2009, 12, 18), 162);

        // Mostrar el objeto Pelicula
        System.out.println(pelicula2.toString());

        // Modificar los atributos que son modificables
        pelicula2.setFechaEstreno(LocalDate.of(2009, 11, 18));
        pelicula2.setDuracion(163);

        // Mostrar el objeto Pelicula después de modificar los atributos
        System.out.println(pelicula2.toString());
        
        Pelicula pelicula3 = new Pelicula("John Wick");
        System.out.println(pelicula3.toString());
    }
}
