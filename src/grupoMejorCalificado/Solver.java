package grupoMejorCalificado;

import java.util.List;
import java.util.Set;
import objetos.Grupo;
import objetos.Persona;
import objetos.Requerimiento;

public class Solver extends Thread
{
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Requerimiento requeridos;
	private Grupo actual;
	private Grupo grupoMayorPuntuacion;
	
	public Solver(	List<Persona> personas,
					List<List<Integer>> incompatibles,
					Requerimiento requeridos,
					Grupo grupoMayorPuntuacion)
	{
		setPersonas(personas);
		setIncompatibles(incompatibles);
		setRequeridos(requeridos);
		setGrupoMayorPuntuacion(grupoMayorPuntuacion);
	}

	@Override
	public void run()
	{
		generarMejorEquipo();
	}
	
	public void generarMejorEquipo()
	{
		actual = new Grupo();
		
		generarDesde(0);
	}
	
	private void generarDesde(int id)
	{
		// caso base 1 : llegamos a una hoja o el grupo actual tiene el tamanio requerido
		if (id == personas.size() || actual.getTamano() == requeridos.getTamano())
		{
			if (cumpleTodosRequisitos() && actual.getPuntuacion() > grupoMayorPuntuacion.getPuntuacion())
				grupoMayorPuntuacion.clonar(actual);
			
			return;
		}
		
		// caso base 2 : si hay conflicto entre la persona a agregar y las que ya están en actual
		if (hayConflicto(id, actual.getPersonas()))
			return;
			
		actual.agregar(personas.get(id));
		generarDesde(id+1);
			
		actual.quitar(personas.get(id));
		generarDesde(id+1);
	}
	
	private boolean cumpleTodosRequisitos()
	{
		return actual.getCantLiderProyecto() == requeridos.getCantLiderProyecto()
				&& actual.getCantArquitectos() == requeridos.getCantArquitectos()
				&& actual.getCantDevelopers() == requeridos.getCantDevelopers()
				&& actual.getCantTesters() == requeridos.getCantTesters();
	}
	
	private boolean hayConflicto(int id, Set<Persona> personas)
	{
		for (Persona persona : personas)
			if (incompatibles.get(id).contains(persona.getId()))
				return true;
			
		return false;
	}

	public void setPersonas(List<Persona> personas)
	{
		this.personas = personas;
	}

	public void setIncompatibles(List<List<Integer>> incompatibles)
	{
		this.incompatibles = incompatibles;
	}

	public void setRequeridos(Requerimiento requeridos)
	{
		this.requeridos = requeridos;
	}

	public Grupo getGrupoMayorPuntuacion()
	{
		return grupoMayorPuntuacion;
	}
	
	private void setGrupoMayorPuntuacion(Grupo grupoMayorPuntuacion) {
		this.grupoMayorPuntuacion = grupoMayorPuntuacion;
		
	}
}
