package logica;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import objetos.Incompatibilidad;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class Solver
{
	private Set<Persona> listaPersonas;
	private List<Incompatibilidad> incompatibilidades;
	private Requerimiento requerimientos;
	
	public Solver()
	{
		listaPersonas = new HashSet<Persona>();
		incompatibilidades = new ArrayList<Incompatibilidad>();
	}
	
	public void registrarPersona(int id, int rendimiento, String nombre, Rol rol)
	{
		listaPersonas.add(new Persona(id, rendimiento, nombre, rol));
	}
	
	public void registrarIncompatibilidad(int id1, int id2)
	{
		incompatibilidades.add(new Incompatibilidad(id1, id2));
	}
	
	public void registrarRequerimientos(int cantLiderProyecto, int cantArquitectos,
										int cantDevelopers, int cantTesters)
	{
		this.requerimientos = new Requerimiento(cantLiderProyecto, cantArquitectos,
												cantDevelopers, cantTesters);
	}
	
	public List<Persona> generarMejorEquipo()
	{
		throw new RuntimeException("Método no implementado");
	}
	
	public Set<Persona> getListaPersonas()
	{
		return listaPersonas;
	}
	
	public List<Incompatibilidad> getIncompatibilidades()
	{
		return incompatibilidades;
	}
	
	public Requerimiento getRequerimientos()
	{
		return requerimientos;
	}
}
