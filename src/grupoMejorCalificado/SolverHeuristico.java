package grupoMejorCalificado;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import objetos.Grupo;
import objetos.Persona;
import objetos.Requerimiento;

public class SolverHeuristico extends Thread {
	
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Requerimiento requeridos;
	private Grupo actual;
	private Grupo grupoSolucion;

	public SolverHeuristico(	List<Persona> personas,
		List<List<Integer>> incompatibles,
		Requerimiento requeridos) {
		
		setPersonas(personas);
		setIncompatibles(incompatibles);
		setRequeridos(requeridos);
	}
	
	/**
	 * Este metodo
	 * crea una lista para cada Rol
	 * las ordena por rendimiento
	 * selecciona la cantidad indicada para cada Rol, sin agregar incompatibles
	 */
	@Override
	public void run() {
		grupoSolucion = new Grupo();
		List<Persona> lideresProyecto, arquitectos, developers, testers;
		
		lideresProyecto = new ArrayList<>();
		arquitectos = new ArrayList<>();
		developers = new ArrayList<>();
		testers = new ArrayList<>();
		
		separarPorRol(lideresProyecto, arquitectos, developers, testers);
		ordenarPorRendimiento(lideresProyecto, arquitectos, developers, testers);
		agregarLosMejores(lideresProyecto, arquitectos, developers, testers);
	}

	private void agregarLosMejores(List<Persona> lideresProyecto, List<Persona> arquitectos, List<Persona> developers,
			List<Persona> testers) {
		int agregados = 0;
		for (int i = 0; i < lideresProyecto.size(); i++) {
			if ( !hayConflicto(lideresProyecto.get(i).getId(), grupoSolucion.getPersonas()) ) {
				grupoSolucion.agregar(lideresProyecto.get(i));
				agregados++;
			}
			if ( agregados == requeridos.getCantLiderProyecto() )
				break;
		}
		
		agregados = 0;
		for (int i = 0; i < arquitectos.size(); i++) {
			if ( !hayConflicto(arquitectos.get(i).getId(), grupoSolucion.getPersonas()) ) {
				grupoSolucion.agregar(arquitectos.get(i));
				agregados++;
			}
			if ( agregados == requeridos.getCantArquitectos() )
				break;
		}
		
		agregados = 0;
		for (int i = 0; i < developers.size(); i++) {
			if ( !hayConflicto(developers.get(i).getId(), grupoSolucion.getPersonas()) ) {
				grupoSolucion.agregar(developers.get(i));
				agregados++;
			}
			if ( agregados == requeridos.getCantDevelopers() )
				break;
		}
		
		agregados = 0;
		for (int i = 0; i < testers.size(); i++) {
			if ( !hayConflicto(testers.get(i).getId(), grupoSolucion.getPersonas()) ) {
				grupoSolucion.agregar(testers.get(i));
				agregados++;
			}
			if ( agregados == requeridos.getCantTesters() )
				break;
		}
	}

	private void ordenarPorRendimiento(List<Persona> lideresProyecto, List<Persona> arquitectos,
			List<Persona> developers, List<Persona> testers) {
		Collections.sort(lideresProyecto);
		Collections.sort(arquitectos);
		Collections.sort(developers);
		Collections.sort(testers);
	}

	private void separarPorRol(	List<Persona> lideresProyecto, List<Persona> arquitectos,
								List<Persona> developers, List<Persona> testers) {
		
		for (Persona persona : personas) {
			switch (persona.getRol()) {
				case LIDER_DE_PROYECTO:
					lideresProyecto.add(persona);
					break;
				case ARQUITECTO:
					arquitectos.add(persona);
					break;
				case PROGRAMADOR:
					developers.add(persona);
					break;
				case TESTER:
					testers.add(persona);
					break;
			}
			
		}
	}
	
	private boolean hayConflicto(int id, Set<Persona> personas)
	{
		for (Persona persona : personas)
			if (incompatibles.get(id).contains(persona.getId()))
				return true;
		
		return false;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public List<List<Integer>> getIncompatibles() {
		return incompatibles;
	}

	public void setIncompatibles(List<List<Integer>> incompatibles) {
		this.incompatibles = incompatibles;
	}

	public Requerimiento getRequeridos() {
		return requeridos;
	}

	public void setRequeridos(Requerimiento requeridos) {
		this.requeridos = requeridos;
	}

	public Grupo getActual() {
		return actual;
	}

	public void setActual(Grupo actual) {
		this.actual = actual;
	}

	public Grupo getGrupoSolucion() {
		return grupoSolucion;
	}
}
