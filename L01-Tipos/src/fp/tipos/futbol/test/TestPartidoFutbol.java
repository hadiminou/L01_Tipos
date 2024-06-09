package fp.tipos.futbol.test;

import java.time.LocalDateTime;
import java.util.List;

import fp.tipos.futbol.Competicion;
import fp.tipos.futbol.PartidoFutbol;

public class TestPartidoFutbol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PartidoFutbol partido1 = new PartidoFutbol(LocalDateTime.of(2024,9,16,13,25), 
				"Sporting Gijon", "Bacelona", 0, 5);
		PartidoFutbol partido2 = new PartidoFutbol(LocalDateTime.of(2024,8,13,17,30), 
				"Real Madrid", "Ateltico Madrid", 2, 3);
		System.out.println(partido1);
		System.out.println(partido2.toString());
		System.out.println("cadena con formato: "+partido1.getCadenaConFormato());
		
		System.out.println(partido1.equals(partido2));
		System.out.println(partido1.hashCode());
		
		
	}
}
