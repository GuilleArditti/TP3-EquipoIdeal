package logica;

import java.util.List;
import objetos.Persona;
import objetos.Requerimiento;

public class Solver
{
	private List<Persona> listaPersonas;
	private List<Integer> incompatibilidades;
	private Requerimiento requerimientos;
	
	public void registrarPersona()
	{
		throw new RuntimeException("Método no implementado");
	}
	
	public void registrarIncompatibilidad()
	{
		throw new RuntimeException("Método no implementado");
	}
	
	public void registrarRequerimientos()
	{
		throw new RuntimeException("Método no implementado");
	}
	
	public List<Persona> generarMejorEquipo()
	{
		throw new RuntimeException("Método no implementado");
	}
	
	public List<Persona> getListaPersonas()
	{
		return listaPersonas;
	}
	
	public List<Integer> getIncompatibilidades()
	{
		return incompatibilidades;
	}
	
	public Requerimiento getRequerimientos()
	{
		return requerimientos;
	}
}
