package ar.edu.unju.escmi.poo.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtil {

	public static LocalDate convertirFecha(String fecha) throws Exception {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate date = null;
		try {
			LocalDate.parse(fecha,formato);
		}catch(DateTimeParseException dtpe) {
		throw new Exception("La fecha ingresada no tiene el formato deseado");
			//System.out.println(dtpe);
			
		}
		
		return date;
	}

	
	public static String convertirLocalDateString(LocalDate fechaLocalDate) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaString = formato.format(fechaLocalDate);
		return fechaString;
	}
	
	public static int calcularTiempoActivo(LocalDate fechaAlta) {
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaAlta, ahora);
		int tiempo = periodo.getDays();
		return tiempo;
	}
	
	
}
