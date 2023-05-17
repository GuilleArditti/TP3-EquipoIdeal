package logica;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import objetos.Grupo;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class Solver
{
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Grupo actual;
	private Grupo mayorPuntuacion;
	private Requerimiento requeridos;
	
	public Solver()
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
	
	public Set<Persona> generarMejorEquipo()
	{

		actual = new Grupo();
		mayorPuntuacion = new Grupo();
		
		generarDesde(0);
		
		return mayorPuntuacion.getPersonas();
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
	
	private void generarDesde(int id)
	{
		if (id == personas.size() || actual.getTamano() == requeridos.getTamano())
		{
			if (		cumpleTodosRequisitos()
					&& 	actual.getPuntuacion() > mayorPuntuacion.getPuntuacion()
					&& 	!hayConflicto(id, actual.getPersonas()) )
			{
				mayorPuntuacion.clonar(actual);
			}
		}
		else
		{
			actual.agregar(personas.get(id));
			generarDesde(id+1);
			
			actual.quitar(personas.get(id));
			generarDesde(id+1);
		}
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

	private boolean cumpleTodosRequisitos() {
		return actual.getCantLiderProyecto() == requeridos.getCantLiderProyecto()
				&& actual.getCantArquitectos() == requeridos.getCantArquitectos()
				&& actual.getCantDevelopers() == requeridos.getCantDevelopers()
				&& actual.getCantTesters() == requeridos.getCantTesters();
	}
}
