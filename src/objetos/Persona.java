package objetos;

public class Persona
{
	private int id;
	private int rendimiento;
	private String nombre;
	private Rol rol;
	
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
		if ( rendimiento < 1 || rendimiento > 5 )
			throw new IllegalArgumentException("El rendimiento debe estar entre 1 - 5");
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
}
