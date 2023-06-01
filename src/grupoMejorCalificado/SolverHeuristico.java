package grupoMejorCalificado;

import java.util.List;
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
	
	@Override
	public void run() {
		/**
		 * crear una lista para cada Rol
		 * ordenarlas por puntuacion
		 * seleccionar las mejores puntuaciones sin pasarme de los requisitos y sin agregar incompatibles
		 * agregar los seleccionados a un grupo solucion
		 */
		
		List<Persona> lideresProyecto = new ArrayList<>();
		List<Persona> arquitectos = new ArrayList<>();
		List<Persona> developers = new ArrayList<>();
		List<Persona> testers = new ArrayList<>();
		
		grupoSolucion = new Grupo();
		
		separarPorRol(lideresProyecto, arquitectos, developers, testers);
		ordenarPorRendimiento(lideresProyecto, arquitectos, developers, testers);
		agregarLosMejores(lideresProyecto, arquitectos, developers, testers);
	}

	private void agregarLosMejores(List<Persona> lideresProyecto, List<Persona> arquitectos, List<Persona> developers,
			List<Persona> testers) {
		for (int i = 0; i < requeridos.getCantLiderProyecto(); i++)
			if (true/* no hay conflicto */)
				grupoSolucion.agregar(lideresProyecto.get(i));
		
		for (int i = 0; i < requeridos.getCantArquitectos(); i++)
			grupoSolucion.agregar(arquitectos.get(i));
		
		for (int i = 0; i < requeridos.getCantDevelopers(); i++)
			grupoSolucion.agregar(developers.get(i));
		
		for (int i = 0; i < requeridos.getCantArquitectos(); i++)
			grupoSolucion.agregar(testers.get(i));
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

	public Grupo getGrupoMayorPuntuacion() {
		return grupoSolucion;
	}

	public void setGrupoMayorPuntuacion(Grupo grupoMayorPuntuacion) {
		this.grupoSolucion = grupoMayorPuntuacion;
	}
	
	
}
