package ar.edu.unju.escmi.poo.collections;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ar.edu.unju.escmi.poo.dominio.Persona;
import ar.edu.unju.escmi.poo.dominio.Usuario;
public abstract class CollectionPersona {

	public static List<Persona> personas;
	
	public static List<Persona> getPersonas(){
		Persona person, person2;
		if(personas == null) {
			personas = new ArrayList<Persona>();
			CollectionRol.getRoles();
			Usuario user = new Usuario("aabb@gmail.com", "123", true, LocalDate.parse("2022-10-20"), CollectionRol.roles.get(0));
			Usuario user2 = new Usuario("ccdd@gmail.com", "123456", true, LocalDate.parse("2022-10-01"), CollectionRol.roles.get(1));
			 person = new Persona("aaa", "bbb", (long) 5151, user);
			 person2 = new Persona("ccc", "ddd", (long) 1212, user2);
			personas.add(person);
			personas.add(person2);
		}
		return personas;
	}
	public static void agregarPersona(Persona pe) throws Exception {
		personas.add(pe);
			System.out.println("Agregado");
	}
	public static Persona buscarPersona(String email, String password) {
		Persona person = null;
		
		person = personas.stream().filter(p-> p.getUsuario().getEmail().contentEquals(email) && p.getUsuario().getPassword().contentEquals(password)).findFirst().get();
	
		
		return person;
	}
	public static Persona buscarPorEmail(String correo) {
		Persona person = null;
		Optional<Persona> encontrado= Optional.empty();
		try {
			person = personas.stream().filter(p-> p.getUsuario().getEmail().contentEquals(correo)).findFirst().get();
			encontrado =personas.stream().filter(p-> p.getUsuario().getEmail().contentEquals(correo)).findFirst();
			if(encontrado.isPresent()) {
				System.out.println(" ");
			}else {
				System.out.println("El usuario no existe");
			}
		}catch(Exception e) {
			if(e instanceof NoSuchElementException) {
			System.out.println(" ");
			}else {
				System.out.println(e);
			}
		}
		return person;
	}
	public static Optional<Persona> buscarPersonaPorDni(long dni) throws Exception {
		Optional<Persona> encontrado= Optional.empty();
		encontrado.isPresent();
		Persona person = personas.stream().filter(p -> p.getDni() == dni).findFirst().get();
		try {
			
			for(Persona pe : personas) {
				if(pe.getDni() == person.getDni()) {
					encontrado.isEmpty();
						if(encontrado.isEmpty()) {
							System.out.println("DNI registrado anteriormente");
						}else if(encontrado.isPresent()) {
							throw new Exception("Este DNI ya fue registrado");
						}
			}
			}
			}catch (Exception e) {
				if(e instanceof NoSuchElementException) {
					System.out.println("Contrasena no registrada anteriormente");
				}else {
					throw new Exception("Esta contrasena ya fue registrada");
				}
			}
		
		return encontrado;
	}
	public static boolean comprobarExistenciaEmail(String correo) throws Exception {
		boolean band=true;
		Optional<Persona> encontrado= Optional.empty();
		encontrado.isPresent();
		Persona person = personas.stream().filter(p -> p.getUsuario().getEmail().equals(correo)).findFirst().get();
		try {
			
			for(Persona pe : personas) {
				if(pe.getUsuario().getEmail().equals(person.getUsuario().getEmail())) {
					band = false;
						if(band == false) {
							System.out.println("Email  registrado anteriormente");
						}else if(band==true) {
							throw new Exception("Este Email ya fue registrado");
						}
			}
			}
			}catch (Exception e) {
				if(e instanceof NoSuchElementException) {
					System.out.println("Contrasena no registrada anteriormente");
				}else {
					throw new Exception("Esta contrasena ya fue registrada");
				}
		}
		
		return band;
	}
	public static Optional<Persona> comprobarExistenciaContrasena(String contrasenia) throws Exception {
		Optional<Persona> encontrado= Optional.empty();
		encontrado.isPresent();
		Persona person = personas.stream().filter(p -> p.getUsuario().getPassword().equals(contrasenia)).findFirst().get();
		try {
			
			for(Persona pe : personas) {
				if(pe.getUsuario().getPassword().equals(person.getUsuario().getPassword())){
					encontrado.isEmpty();
						if(encontrado.isEmpty()) {
							System.out.println("Contrasena registrada anteriormente");
						}else if(encontrado.isPresent()) {
							throw new Exception("Esta contrasena ya fue registrada");
						}
			}
			}
			}catch (Exception e) {
				if(e instanceof NoSuchElementException) {
					System.out.println("Contrasena no registrada anteriormente");
				}else {
					throw new Exception("Esta contrasena ya fue registrada");
				}
			
			}
		return encontrado;
		
	}
	public static void mostrarDatos(String correo) {
		Persona person = personas.stream().filter(p->p.getUsuario().getEmail().contentEquals(correo)).findFirst().get();
		System.out.println(person.toString());
		System.out.println(person.getUsuario().toString());
		
	}
}
