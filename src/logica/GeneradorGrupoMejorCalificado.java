package logica;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import objetos.Grupo;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class GeneradorGrupoMejorCalificado
{
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Requerimiento requeridos;
	
	public GeneradorGrupoMejorCalificado()
	{
		personas = new ArrayList<>();
		incompatibles = new ArrayList<>();
	}
	
	public void registrarPersona(int rendimiento, String nombre, Rol rol)
	{
		personas.add(new Persona(personas.size(), rendimiento, nombre, rol));
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
		this.requeridos = new Requerimiento(cantLiderProyecto, cantArquitectos,
												cantDevelopers, cantTesters);
	}
	
	public Set<Persona> generarMejorEquipo(Set<Persona> getListaPersonas)
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
		return requeridos;
	}
	
	

	private boolean hayConflicto(int id, Set<Persona> personas2) {
		for (Persona persona : personas2)
		{
			if (incompatibles.get(id).contains(persona.getId()))
			{
				return true;
			}
		}
		
		return false;
	}

	
}
