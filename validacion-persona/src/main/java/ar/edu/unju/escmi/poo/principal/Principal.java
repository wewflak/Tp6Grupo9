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
			
		do {
			System.out.println("\n1. Iniciar Sesion");
			System.out.println("2. Salir");
			
			band=true;

			try {
				opPrimigenio=0;
			opPrimigenio = scan.nextInt();
			}catch(InputMismatchException ime) {
				scan.next();
				System.out.println("Ingrese el tipo correcto de dato");
			}
		switch(opPrimigenio) {
		case 1:
		band=false;
		while(band==false) {
		System.out.println("\n*/*/*/**/*/   Menu   /*/*/*/*/*");
		System.out.println("Ingrese su email");
		correo = scan.next();
		System.out.println("Ingrese su contrasena");
		contrasena = scan.next();
		try {
		person = CollectionPersona.buscarPersona(correo, contrasena);
		person.getUsuario().cambiarEstado(person);
		band=true;
		if(person.getUsuario().isEstado()==true) {
			
		if(person.getUsuario().getRol().getDescripcion().contentEquals("Admin")) {
			try {
			do {
				System.out.println("\n------ Menu Administrador ------");
				System.out.println("1. Alta de persona");
				System.out.println("2. Buscar un usuario por su email y mostrar todos sus datos");
				System.out.println("3. Extender tiempo de un usuario");
				System.out.println("4. Salir");
				try {
				op=scan.nextInt();
				}catch(Exception e) {
					if(e instanceof InputMismatchException) {
						scan.next();
						System.out.println("Ingrese el tipo de dato correcto");
					}else {
						System.out.println(e);
					}
				}
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
				try {
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
					}else {System.out.println("\n");}
					}
				}else {System.out.println("\n");}
					}
					}
					else {System.out.println("\n");}
				}
				}catch(InputMismatchException ime) {
					System.out.println("Ingrese el tipo correcto de dato");
					scan.next();
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
						band=true;
					}catch(Exception e){
						if(e instanceof NoSuchElementException) {
						System.out.println("  ");
					}else {
						System.out.println(e);
					}
					}
				}
				break;
			
			case 3:
				System.out.println("Ingrese el email del usuario");
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
				System.out.println("\nSesion cerrada exitosamente");
			break;
			default:
				System.out.println("\nIngrese una de las opciones presentadas");
				break;
			}
			}while(op!=4);
			}
		catch(InputMismatchException ime) {
			scan.next();
			System.out.println("Ingrese el tipo correcto de dato");
		}
			}
		else if(person.getUsuario().getRol().getDescripcion().contentEquals("Cliente")) {
			op=0;
			do {
				System.out.println("\n****** Menu Cliente ******");
				System.out.println("1. Cambiar datos de usuario");
				System.out.println("2. Mostrar datos de tu usuario");
				System.out.println("3. Salir");
				try {
					op = scan.nextInt();
					switch(op) {
					case 1:
						op1=0;
						do {
							System.out.println("\n1. Cambiar datos de persona");
							System.out.println("2. Cambiar datos de usuario");
							System.out.println("3. Salir");
						try {
							op1 = scan.nextInt();
							switch(op1) {
							case 1:
								int op2=0;
								do {
									System.out.println("\n1. Cambiar nombre");
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
										scan.next();
										System.out.println("Ingrese el tipo correcto de dato");
									}
								}while(op2!=4);
								break;
							case 2:			
								int op3=0;
							do {
								System.out.println("\n1. Cambiar email");
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
										System.out.println("\nIngrese una de las opciones presentadas");
									break;
									}
								}catch(InputMismatchException e) {
									scan.next();
									System.out.println("Ingrese el tipo correcto de dato");
								}
							}while(op3!=3);
								break;
							default:
								System.out.println("\nIngrese una de las opciones presentadas");
							break;
							}
							
						}catch(InputMismatchException e) {
							scan.next();
							System.out.println("\nIngrese el tipo correcto de dato\n");
						}
						
						}while(op1!=3);
						break;
					case 2:
						CollectionPersona.mostrarDatos(person.getUsuario().getEmail());
						break;
					case 3:
						System.out.println("\nSesion cerrada exitosamente");
						break;
					default:
						System.out.println("\nIngrese una de las opciones presentadas");
						break;
					}
				}catch(InputMismatchException e) {
					scan.next();
					System.out.println("\nIngrese el tipo correcto de dato");
					
				}
			}while(op!=3);
		}
		}else {
			person.getUsuario().cambiarEstado(person);
			System.out.println("\nSu tiempo activo ya expiro");
		}
	}catch(Exception e) {
		if(e instanceof NoSuchElementException) {
		System.out.println("\nNo existe esa persona");
		}else {
			System.out.println(e);
		}
	}
		}
		break;
		case 2:
			System.out.println("Programa finalizado");
			break;
		default:
			System.out.println("\nIngrese una de las opciones presentadas");
			break;
		}band=true;
			
		}while(opPrimigenio!=2);
		
	}while(band==false);

scan.close();
		///Maven build-- Goals = package

		
}

}
