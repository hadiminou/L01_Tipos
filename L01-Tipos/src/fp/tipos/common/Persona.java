package fp.tipos.common;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fp.utiles.Checkers;

public record Persona(String nombre, String apellidos, String dni, LocalDate fechaNacimiento)
	implements Comparable<Persona> {

	
	public Integer getEdad() {
		Integer edad = (int) ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
		return edad;
	}
	
	public Persona{
		Checkers.check("el DNI debe estar compuesto por 9 caracteres, los ocho primeros deben ser "
				+ "números, y el último una letra", dni.length() == 9 
				&& Character.isAlphabetic(dni.charAt(8))
				&& Character.isDigit(dni.charAt(0)) && Character.isDigit(dni.charAt(1))
				&& Character.isDigit(dni.charAt(2)) && Character.isDigit(dni.charAt(3))
				&& Character.isDigit(dni.charAt(4)) && Character.isDigit(dni.charAt(5))
				&& Character.isDigit(dni.charAt(6)) && Character.isDigit(dni.charAt(7)));
		Checkers.check("la fecha de nacimiento debe ser anterior a la fecha actual",
				fechaNacimiento.isBefore(LocalDate.now()));
		//Checkers.check("el email debe contener el carácter ‘@’", email.contains("@"));
	}

	// No hace falta anadir equals en Record, verdad??
	
	@Override
	public int compareTo(Persona p) {
		int res = dni.compareTo(p.dni);
		return res;
	}
	
}
