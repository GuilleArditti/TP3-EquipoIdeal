package objetos;

import javax.swing.ImageIcon;

public class Persona implements Comparable<Persona> {
	private int id;
	private int rendimiento;
	private String nombre;
	private Rol rol;
	private ImageIcon fotoDePerfil;

	public Persona(int id, int rendimiento, String nombre, Rol rol) {
		setId(id);
		setRendimiento(rendimiento);
		setNombre(nombre);
		setRol(rol);
	}

	@Override
	public String toString() {
		return id + " " + nombre + "(" + rendimiento + ")";
	}

	@Override
	public boolean equals(Object other) {
		Persona otra = (Persona) other;

		return this.rendimiento == otra.getRendimiento() && this.nombre.equals(otra.getNombre())
				&& this.rol.equals(otra.getRol());
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public ImageIcon getFotoDePerfil() {
		return fotoDePerfil;
	}

	public void setFotoDePerfil(ImageIcon fotoDePerfil) {
		this.fotoDePerfil = fotoDePerfil;
	}

	@Override
	public int compareTo(Persona o) {
		if (this.rendimiento > o.getRendimiento())
			return -1;

		if (this.rendimiento < o.getRendimiento())
			return 1;

		return 0;
	}

}
