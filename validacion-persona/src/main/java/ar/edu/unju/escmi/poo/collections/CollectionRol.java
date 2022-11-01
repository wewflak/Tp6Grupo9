package ar.edu.unju.escmi.poo.collections;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.poo.dominio.Rol;
public abstract class CollectionRol {
	public static List<Rol> roles;
	public static void getRoles() {
		if(roles == null) {
			roles = new ArrayList<Rol>();
			Rol ro = new Rol("Admin");
			roles.add(ro);
			Rol role = new Rol("Cliente");
			roles.add(role);
		}
	}
}
