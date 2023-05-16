package logica;

import java.util.List;
import java.util.ArrayList;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class Solver
{
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Requerimiento requerimientos;
	
	public Solver()
	{
		personas = new ArrayList<>();
		incompatibles = new ArrayList<>();
	}
	
	public void registrarPersona(int id, int rendimiento, String nombre, Rol rol)
	{
		personas.add(new Persona(id, rendimiento, nombre, rol));
	}
	
	public void registrarIncompatibilidad(int id, int idIncompatible)
	{
		if ( incompatibles.get(id) == null )
			incompatibles.add(id, new ArrayList<Integer>());
		
		incompatibles.get(id).add(idIncompatible);	
	}
	
	public void registrarRequerimientos(int cantLiderProyecto, int cantArquitectos,
										int cantDevelopers, int cantTesters)
	{
		this.requerimientos = new Requerimiento(cantLiderProyecto, cantArquitectos,
												cantDevelopers, cantTesters);
	}
	
	// TODO
	public List<Persona> generarMejorEquipo()
	{
		throw new RuntimeException("Método no implementado");
	}
	
	public List<Persona> getListaPersonas()
	{
		return personas;
	}
	
	public List<Integer> getIncompatibilidades(int id)
	{
		return incompatibles.get(id);
	}
	
	public Requerimiento getRequerimientos()
	{
		return requerimientos;
	}
}
