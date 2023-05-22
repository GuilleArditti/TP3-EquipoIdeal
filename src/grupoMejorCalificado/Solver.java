package grupoMejorCalificado;

import java.util.Set;
import objetos.Grupo;
import objetos.Persona;
import objetos.Requerimiento;

public class Solver
{
	private Grupo actual;
	private Grupo mayorPuntuacion;
	private Requerimiento requeridos;
	/**
	public Set<Persona> generarMejorEquipo(Set<Persona> getListaPersonas)
	{

		actual = new Grupo();
		mayorPuntuacion = new Grupo();
		
		generarDesde(0, getListaPersonas);
		
		return mayorPuntuacion.getPersonas();
	}
	
	private void generarDesde(int id, Set<Persona> getListaPersonas)
	{
		// caso base 1 : llegamos a una hoja o el grupo actual tiene el tamanio requerido
		if (id == getListaPersonas.size() || actual.getTamano() == requeridos.getTamano()) {
			if (cumpleTodosRequisitos() && actual.getPuntuacion() > mayorPuntuacion.getPuntuacion())
				mayorPuntuacion.clonar(actual);
			return;
		}
		
		// caso base 2 : si hay conflicto entre la persona a agregar y las que ya están en actual
		if (hayConflicto(id, actual.getPersonas()))
			return;
		
		actual.agregar(personas.get(id));
		generarDesde(id+1);
			
		actual.quitar(personas.get(id));
		generarDesde(id+1);
		
	}*/
	
	private boolean cumpleTodosRequisitos() {
		return actual.getCantLiderProyecto() == requeridos.getCantLiderProyecto()
				&& actual.getCantArquitectos() == requeridos.getCantArquitectos()
				&& actual.getCantDevelopers() == requeridos.getCantDevelopers()
				&& actual.getCantTesters() == requeridos.getCantTesters();
	}
}
