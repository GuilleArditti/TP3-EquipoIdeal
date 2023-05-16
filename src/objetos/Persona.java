package objetos;

import java.util.List;

public class Persona
{
	private int id;
	private int rendimiento;
	private String nombre;
	private Rol rol;
	private List<Integer> incompatibles;
	
	public Persona(int id, int rendimiento, String nombre, Rol rol)
	{
		setId(id);
		setRendimiento(rendimiento);
		setNombre(nombre);
		setRol(rol);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getRendimiento()
	{
		return rendimiento;
	}

	public void setRendimiento(int rendimiento)
	{
		this.rendimiento = rendimiento;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public Rol getRol()
	{
		return rol;
	}

	public void setRol(Rol rol)
	{
		this.rol = rol;
	}
	
	public List<Integer> getIncompatibles()
	{
		return incompatibles;
	}
	
	public void agregarIncompatible(int id)
	{
		incompatibles.add(id);
	}
}
