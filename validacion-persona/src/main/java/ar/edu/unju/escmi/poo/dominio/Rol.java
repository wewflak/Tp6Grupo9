package ar.edu.unju.escmi.poo.dominio;

public class Rol {

	private String descripcion;
	public static final  int DURACION_ROL = 20;

	
	public Rol() {
		// TODO Auto-generated constructor stub
	}


	public Rol(String descripcion) {
		super();
		this.descripcion = descripcion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public static int getDuracionRol() {
		return DURACION_ROL;
	}
	
	
}
