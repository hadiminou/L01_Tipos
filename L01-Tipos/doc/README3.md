## Fundamentos de Programación
# Ejercicio de laboratorio: Diseño de tipos III
**Autores:** Toñi Reina, Mariano González.
**Revisor:** José María Luna. 
**Última modificación:** 05/03/2024

**Objetivos:**
- Implementar una factoría para la lectura de datos de un fichero.
- Añadir tratamientos secuenciales con bucles al tipo agregado (contenedor).


**1. Añade una factoría para la lectura de datos de un fichero.**

- Crea una factoría para cada uno de los tipos definidos en el laboratorio "Diseño de Tipos I", para que se puedan leer los datos de un fichero CSV y almacenarlos en el tipo agregado correspondiente.
- La factoría debe contener, al menos, dos métodos: uno que lee el fichero y devuelve una lista de objetos del tipo, y otro que *parsea* una línea del fichero y obtiene un objeto del tipo a partir de ella.
- Puede utilizar la clase de utilidad *Ficheros* para leer las líneas del fichero. Prueba la factoría en el test del tipo agregado, leyendo el fichero y comprobando que se han leído correctamente todos los datos.

**2. Añade tratamientos secuenciales con bucles al tipo agregado.**

- Implementa y prueba los tratamientos secuenciales con bucles sobre los tipos agregados que se indican al final del documento. En los tipos agregados para los que no se proponen tratamientos, diseña tú mismo unos tratamientos basándote en los propuestos para los otros tipos.

## **Factoría para los tipos de Diseño de Tipos I:**

1. **Cancion**
    - *Formato como cadena:* “título; artista; duración; fecha de lanzamiento; género”.
    - Ejemplo: `“Wanna Be Startin' Something; Michael Jackson; 6:03; 1982/11/30; Pop”`.

1. **Pelicula**
    - *Formato como cadena:* ”título; duración; fecha de estreno”.
    - Ejemplo: `“Baby's Dinner;1 min;28/12/1895”`.

1. **PartidoFutbol** 
    - *Formato como cadena:* ”fecha; equipo local; equipo visitante; goles local; goles visitante”.
    - Ejemplo: `“20/08/2018;Ath Bilbao;Leganes;2;1”`.

1. **Hotel**
    - *Formato como cadena:* “nombre, direccion, ciudad, telefono, categoria, tipoAlojamiento, categoriaPrecio, puntuacion, fechaApertura, numComentarios, admiteMascotas, adaptado, latitud, longitud, puntuacion, cadenaHotelera”.
    - Ejemplo: `“Viceroy L'Ermitage Beverly Hills, 9291 Burton Way, State of California, +34970621239, OTROS, BED_AND_BREAKFAST, ALTA, 8.14, 1993-05-01, 37711, false, false, 48.9361342, 24.1594075, 8.14, Silken”`.

1. **Vuelo** 
    - *Formato como cadena:* ”origen; destino; número de plazas; número de pasajeros; código; fecha de salida”.
    - Ejemplo: `“Sevilla;Madrid;300;250;IBE-123;01/06/2020 21:00”`.

1. **Beca**
    - *Formato como cadena:* ”código, cuantía total, duración, tipo”.
    - Ejemplo: `“SBB1102, 1750, 5, MOVILIDAD”`.

1. **Persona**
    - *Formato como cadena:* ”dni, nombre, apellidos, fecha de nacimiento”.
    - Ejemplo: `“12345678Z,Juan,López García,20/7/1998”`.

## **Factoría para el tipo TrayectoTren**

1. **TrayectoTren**
    - *Formato como cadena:* “Código del Tren,Nombre del Trayecto,Tipo,Paradas(estacion;hora llegada; hora salida”.
    - Ejemplo:`“Nº 02471,TRAYECTO SEVILLA-MADRID - AV City,AV_CITY,SEVILLA-SANTA JUSTA;-;07:00|CÓRDOBA;07:45;07:50|VILLANUEVA DE CÓRDOBA-LOS PEDROCHES;08:13;08:14|PUERTOLLANO;08:40;08:41|CIUDAD REAL;08:57;08:58|MADRID-PUERTA DE ATOCHA;10:02;-”`.

## **Tratamientos secuenciales para los tipos agregados**

1. **ListaReproduccion**
    - *Integer getNumeroCancionesComienzanPorCadena(String cadena)*: cuenta el número de canciones que comienzan por la cadena dada como parámetro.
    - *Duration getDuracionTotal()*: obtiene la duración total de la lista de reproducción.
    - *Duration getDuracionMedia()*: obtiene la duración media de las canciones de la lista de reproducción.
    - *List\<Cancion\> getCancionesGenero(Genero genero)*: obtiene una lista con las canciones del género dado como parámetro.
    - *Boolean existeCancionConPalabraEnTitulo(String palabra)*: devuelve *true* si existe una canción cuyo título contiene la palabra dada como parámetro, y *false* en caso contrario.
    - *Boolean todasSuperioresA(Integer minutos)*: devuelve *true* si todas las canciones tienen una duración igual o superior a la dada como parámetro, y *false* en caso contrario.
    - *Cancion getCancionMayorDuracion()*: obtiene la canción de mayor duración.
    - *Map<Integer, List\<Cancion\>> agrupaCancionesPorAño()*: crea un Map que hace corresponder a cada año una lista con las canciones de ese año.
    - *Map<Integer, List\<Cancion\>> agrupaCancionesArtistaPorPalabras(String artista)*: crea un Map que hace corresponder a cada número de palabras una lista con las canciones del artista dado como parámetro cuyos títulos tienen ese número de palabras.
    - *Map<String, Long> getNumeroCancionesPorArtista()*: crea un Map que relaciona cada artista con el número de canciones del artista que hay en la lista de reproducción.
    - *Map<Genero, Duration> getDuracionCancionesPorGenero()*: crea un Map que relaciona cada género con la duración total de las canciones de ese género.

1. **Competicion**
    - *Integer getVictoriasVisitantes()*: obtiene el número total de victorias obtenidas por los equipos visitantes.
    - *Integer getTotalGolesMarcados(String equipo)*:* obtiene el número total de goles marcados por el equipo dado como parámetro.
    - *Set\<String\> getEquipos()*: obtiene un* conjunto con los nombres de todos los equipos que participan en la competición.
    - *PartidoFutbol getPartidoMasGoles()*:* obtiene el partido en el que se han marcado más goles.
    - *Integer getPuntosEquipoFecha(String equipo, LocalDate fecha)*: obtiene el número total de puntos obtenidos por el equipo dado como parámetro hasta la fecha dada como parámetro. Se consideran los partidos anteriores a esa fecha.
    - *Map<ResultadoQuiniela, Integer> contarNumPartidosPorResultado()*: crea un Map que relaciona cada resultado con el número de partidos en los que se ha dado ese resultado.
    - *Map <String, Integer> getClasificacionFinal()*: crea un Map que relaciona cada nombre de un equipo con el total de puntos obtenidos en la competición por ese equipo.
    - *Map<String, Integer> contarPartidosGanadosPorEquipo()*: crea un Map que relaciona cada equipo con el número de partidos ganados por ese equipo.
    - *SortedMap<LocalDate, List\<PartidoFutbol\>> getPartidosPorFecha  ()*: crea un SortedMap que relaciona cada fecha con los partidos que se disputaron en esa fecha.
    - *SortedMap<Integer, Set\<String\>> equiposPorNumeroPartidosGanados()*: crea un SortedMap que relaciona cada número con el conjunto de los equipos que han ganado ese número de partidos.
    - *String getCampeon()*: obtiene el nombre del equipo ganador de la competición.
