package ar.edu.unju.escmi.poo.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Usuario {
	
	private String email;
	private String password;
	private boolean estado;
	private LocalDate fechaAlta;
	private Rol rol;
	
public Usuario() {
	// TODO Auto-generated constructor stub
}

public Usuario(String email, String password, boolean estado, LocalDate fechaAlta, Rol rol) {
	super();
	this.email = email;
	this.password = password;
	this.estado = estado;
	this.fechaAlta = fechaAlta;
	this.rol = rol;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public boolean isEstado() {
	return estado;
}

public void setEstado(boolean estado) {
	this.estado = estado;
}

public LocalDate getFechaAlta() {
	return fechaAlta;
}

public void setFechaAlta(LocalDate fechaAlta) {
	this.fechaAlta = fechaAlta;
}

public Rol getRol() {
	return rol;
}

public void setRol(Rol rol) {
	this.rol = rol;
}

@Override
public String toString() {
	return "Usuario [email=" + email + ", password=" + password + ", estado=" + estado + ", fechaAlta=" + fechaAlta
			+ ", rol=" + rol.getDescripcion() + "]";
}

public boolean controlarDuracionRol() {
    try {
    	long periodoActivo = ChronoUnit.DAYS.between(this.fechaAlta, LocalDate.now());
        if (periodoActivo <= 20) {
            return true;
        } else {
            return false;
        }
    } catch (Exception e) {
        return false;
    }
}
public void cambiarEstado(Persona person) {
	Period period = Period.between(this.fechaAlta, LocalDate.now());
	if(controlarDuracionRol()==true) {
		person.getUsuario().setEstado(true);
	}else {
		person.getUsuario().setEstado(false);
	}
}
public void cambiarFechaAlta(Persona person) {
	person.getUsuario().setFechaAlta(LocalDate.now());
}


	
}
