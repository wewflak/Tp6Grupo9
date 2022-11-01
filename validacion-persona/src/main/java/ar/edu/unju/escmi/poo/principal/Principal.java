package ar.edu.unju.escmi.poo.principal;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import ar.edu.unju.escmi.poo.collections.CollectionPersona;
import ar.edu.unju.escmi.poo.collections.CollectionRol;
import ar.edu.unju.escmi.poo.dominio.Persona;
import ar.edu.unju.escmi.poo.dominio.Rol;
import ar.edu.unju.escmi.poo.dominio.Usuario;
import ar.edu.unju.escmi.poo.util.CLS;
import ar.edu.unju.escmi.poo.util.FechaUtil;

public class Principal {

	public static void main(String[] args) throws Exception {
		CollectionPersona.getPersonas();
		CollectionRol.getRoles();
		final String ESC = "\033[";
		String fecha2;
		Persona person = null;
		boolean band=false, tiempoActivo = false;
		int op=0,op1=0, opPrimigenio=0;
		LocalDate date = null;
		String correo, contrasena;
		Scanner scan = new Scanner( System.in);
		// TODO Auto-generated method stub
do {
		try {
			
		do {
			System.out.println("1. Iniciar Sesion");
			System.out.println("2. Salir");
			band=true;
			opPrimigenio = scan.nextInt();
		switch(opPrimigenio) {
		case 1:
		band=false;
		while(band==false) {
		System.out.println("*/*/*/**/*/   Menu   /*/*/*/*/*");
		System.out.println("Ingrese su email");
		correo = scan.next();
		System.out.println("Ingrese su contrasena");
		contrasena = scan.next();
		try {
			band=true;
		person = CollectionPersona.buscarPersona(correo, contrasena);
		System.out.println("Se encontro la persona " + person.getUsuario().getRol().getDescripcion());
		person.getUsuario().controlarDuracionRol();
		if(person.getUsuario().controlarDuracionRol() == true) {
			person.getUsuario().cambiarEstado(person);
		if(person.getUsuario().getRol().getDescripcion().contentEquals("Admin")) {
			try {
			do {
				System.out.println("------ Menu Administrador ------");
				System.out.println("1. Alta de persona");
				System.out.println("2. Buscar un usuario por su email y mostrar todos sus datos");
				System.out.println("3. Extender tiempo de un usuario");
				System.out.println("4. Salir");
				op=scan.nextInt();
			switch(op) {
			
			case 1:
				band= false;
				boolean band2=false,band3=false,band4=false;
				while(band==false) {
				String nm, ap, em, cs, fecha, ro;
				LocalDate date1;
				long dn;
					
				System.out.println("Ingrese el nombre de la persona para el usuario");
				nm = scan.next();
				System.out.println("Ingrese el apellido de la persona para el usuario");
				ap = scan.next();
				System.out.println("Ingrese el dni de la persona para el usuario");
				dn = scan.nextLong();
				try {
				CollectionPersona.buscarPersonaPorDni(dn);
				}catch(Exception e) {
					if(e instanceof NoSuchElementException) {
				
				System.out.println("Ingrese el email del usuario");
				em = scan.next();
				try {
				band2 = CollectionPersona.comprobarExistenciaEmail(em);
					}catch(Exception e2) {
						if(e2 instanceof NoSuchElementException) {
					
				System.out.println("Ingrese la contrasena del usuario");
				cs = scan.next();
				try {
				CollectionPersona.comprobarExistenciaContrasena(cs);
				}catch(Exception e3) {
					if(e3 instanceof NoSuchElementException) {
				date1 = LocalDate.now();
				System.out.println("Ingrese el rol del usuario");
				System.out.println("1. Admin");
				System.out.println("2. Cliente");
				op1 =scan.nextInt();
				switch(op1) {
				case 1:
					Rol rol1 = CollectionRol.roles.get(0);
					Usuario usr = new Usuario(em, cs, true, date1, rol1);
					Persona person1 = new Persona(nm, ap, dn, usr);
					CollectionPersona.agregarPersona(person1);
					break;
				case 2:

					Rol rol2 = CollectionRol.roles.get(1);
					Usuario usr2 = new Usuario(em, cs, true, date1, rol2);
					Persona person2 = new Persona(nm, ap, dn, usr2);
					CollectionPersona.agregarPersona(person2);
					break;
				default:
					System.out.println("Elija una de las opciones presentadas");
					break;
				}
				band=true;
					}else {System.out.println("/n");}
					}
				}else {System.out.println("\n");}
					}
					}
					else {System.out.println("\n");}
				}
				}
				break;
			case 2:
				
				band= false;
				while(band==false) {
					try {
						System.out.println("Ingrese un email");
						correo = scan.next();
						person = CollectionPersona.buscarPorEmail(correo);
						CollectionPersona.mostrarDatos(correo);
						System.out.println(FechaUtil.calcularTiempoActivo(person.getUsuario().getFechaAlta()));
						band=true;
					}catch(Exception e){
						System.out.println(e);
					}
				}
				break;
			
			case 3:
				System.out.println("Ingrese el email del nuevo usuario");
				correo = scan.next();
				person = CollectionPersona.buscarPorEmail(correo);
				try {
				person.getUsuario().cambiarFechaAlta(person);
				System.out.println("Nueva fecha de alta: " + FechaUtil.convertirLocalDateString(person.getUsuario().getFechaAlta()));
				}catch(Exception  e) {
					System.out.println(e.toString());
				}
				break;
			case 4:
				System.out.println("Programa finalizado con exito");
			break;
			default:
				System.out.println("Ingrese una opcion deseada");
				break;
			}
			}while(op!=4);
			}
		catch(InputMismatchException ime) {
			System.out.println("Ingrese el tipo correcto de dato");
		}
			}
		else if(person.getUsuario().getRol().getDescripcion().contentEquals("Cliente")) {
			op=0;
			do {
				System.out.println("****** Menu Cliente ******");
				System.out.println("1. Cambiar datos de usuario");
				System.out.println("2. Mostrar datos de tu usuario");
				System.out.println("3. Salir");
				try {
					op = scan.nextInt();
					switch(op) {
					case 1:
						op1=0;
						do {
							System.out.println("1. Cambiar datos de persona");
							System.out.println("2. Cambiar datos de usuario");
							System.out.println("3. Salir");
						try {
							op1 = scan.nextInt();
							switch(op1) {
							case 1:
								int op2=0;
								do {
									System.out.println("1. Cambiar nombre");
									System.out.println("2. Cambiar apellido");
									System.out.println("3. Cambiar DNI");
									System.out.println("4. Salir del programa");
									try {
										op2 = scan.nextInt();
										switch(op2) {
										case 1:
											System.out.println("Ingrese el nuevo nombre");
											person.setNombre(scan.next());
											break;
										case 2:
											System.out.println("Ingrese el nuevo apellido");
											person.setApellido(scan.next());
											break;
										case 3:
											System.out.println("Ingrese el nuevo DNI");
											person.setDni(scan.nextLong());
											break;
										case 4:
											System.out.println("Programa finalizado");
											break;
										default:
												System.out.println("Ingrese una de las opciones presentadas");
											break;
											}
									}catch(Exception e) {
										e.toString();
									}
								}while(op2!=4);
								break;
							case 2:			
								int op3=0;
							do {
								System.out.println("1. Cambiar email");
								System.out.println("2. Cambiar contrasenia");
								System.out.println("3. Salir");
								try {
									op3 = scan.nextInt();
									switch(op3) {
									case 1:

										String mail= " ";
										System.out.println("Ingrese el nuevo email");
										try { 
											mail= scan.next();
										CollectionPersona.comprobarExistenciaEmail(mail);
										
										}catch(Exception e) {
											if(e instanceof NoSuchElementException) {
											person.getUsuario().setEmail(mail);
											}else {
											System.out.println(e.toString());
										}
										}
										break;
									case 2:
										String contra="";
										System.out.println("Ingrese la nueva contrasenia");
										try {
											contra=scan.next();
										CollectionPersona.comprobarExistenciaContrasena(contra);
										
										}catch(Exception e) {
											if(e instanceof NoSuchElementException) {
												person.getUsuario().setPassword(contra);
												}else {
												System.out.println(e.toString());
											}
										}
										break;
									default:
										System.out.println("Ingrese una de las opciones presentadas");
									break;
									}
								}catch(InputMismatchException e) {
									System.out.println(e.toString());
								}
							}while(op3!=3);
								break;
							default:
								System.out.println("Ingrese una de las opciones presentadas");
							break;
							}
							
						}catch(InputMismatchException e) {
							System.out.println(e.toString());
						}
						
						}while(op1!=3);
						break;
					case 2:
						CollectionPersona.mostrarDatos(person.getUsuario().getEmail());
						break;
					}
				}catch(InputMismatchException e) {
					System.out.println(e.toString());
				}
			}while(op!=3);
		}
		}else {
			person.getUsuario().cambiarEstado(person);
			System.out.println("Su tiempo activo ya expiro");
		}
	}catch(NoSuchElementException nse) {
		System.out.println(nse);
		System.out.println("No existe esa persona");
		System.out.print(ESC + "2J"); 
	}
scan.close();
		}
		break;
		case 2:
			System.out.println("Programa finalizado");
			break;
		}band=true;
			
		}while(opPrimigenio!=2);
		}catch(InputMismatchException ime) {
				System.out.println("Ingrese el tipo correcto de dato");
			}
	}while(band==false);
		
		///Maven build-- Goals = package

		
}
	public static void clrscr(){

	    //Clears Screen in java

	    try {

	        if (System.getProperty("os.name").contains("Windows"))

	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

	        else

	            Runtime.getRuntime().exec("clear");

	    } catch (IOException | InterruptedException ex) {}

	}

}
