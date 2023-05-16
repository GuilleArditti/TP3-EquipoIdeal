package objetos;

public class Persona
{
	private int id;
	private int rendimiento;
	private Rol rol;
	
	public Persona(int id, int rendimiento, Rol rol)
	{
		setId(id);
		setRendimiento(rendimiento);
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

	public Rol getRol()
	{
		return rol;
	}

	public void setRol(Rol rol)
	{
		this.rol = rol;
	}
}
