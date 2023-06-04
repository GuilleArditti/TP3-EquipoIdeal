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

	public SolverHeuristico(List<Persona> personas, List<List<Integer>> incompatibles, Requerimiento requeridos) {

		setPersonas(personas);
		setIncompatibles(incompatibles);
		setRequeridos(requeridos);
	}

	/**
	 * Este metodo crea una lista para cada Rol, las ordena por rendimiento y
	 * selecciona la cantidad indicada para cada Rol, sin agregar incompatibles.
	 */
	@Override
	public void run() {
		grupoSolucion = new Grupo();
		List<Persona> lideresProyecto, arquitectos, programadores, testers;

		lideresProyecto = new ArrayList<>();
		arquitectos = new ArrayList<>();
		programadores = new ArrayList<>();
		testers = new ArrayList<>();

		separarPorRol(lideresProyecto, arquitectos, programadores, testers);
		ordenarPorRendimiento(lideresProyecto, arquitectos, programadores, testers);
		agregarLosMejores(lideresProyecto, arquitectos, programadores, testers);
		
		if (!tieneCantEspecialistasRequerida())
			throw new RuntimeException("No hay suficientes especialistas para cubrir los los puestos.");
	}

	private void agregarLosMejores(List<Persona> lideresProyecto, List<Persona> arquitectos,
			List<Persona> programadores, List<Persona> testers) {
		agregarLosMejoresPorRol(lideresProyecto, requeridos.getCantLiderProyecto());
		agregarLosMejoresPorRol(arquitectos, requeridos.getCantArquitectos());
		agregarLosMejoresPorRol(programadores, requeridos.getCantProgramadores());
		agregarLosMejoresPorRol(testers, requeridos.getCantTesters());
	}

	private void agregarLosMejoresPorRol(List<Persona> especialistas, int cantRequerida) {
		int agregados = 0;
		Persona persona;
		for (int i = 0; i < especialistas.size(); i++) {
			persona = especialistas.get(i);
			if (!hayConflicto(persona.getId(), grupoSolucion.getPersonas())) {
				grupoSolucion.agregar(persona);
				agregados++;
			}
			if (agregados == cantRequerida)
				break;
		}
	}

	private void ordenarPorRendimiento(List<Persona> lideresProyecto, List<Persona> arquitectos,
			List<Persona> programadores, List<Persona> testers) {
		Collections.sort(lideresProyecto);
		Collections.sort(arquitectos);
		Collections.sort(programadores);
		Collections.sort(testers);
	}

	private void separarPorRol(List<Persona> lideresProyecto, List<Persona> arquitectos, List<Persona> programadores,
			List<Persona> testers) {

		for (Persona persona : personas) {
			switch (persona.getRol()) {
			case LIDER_DE_PROYECTO:
				lideresProyecto.add(persona);
				break;
			case ARQUITECTO:
				arquitectos.add(persona);
				break;
			case PROGRAMADOR:
				programadores.add(persona);
				break;
			case TESTER:
				testers.add(persona);
				break;
			}

		}
	}
	
	private boolean tieneCantEspecialistasRequerida() {
		return 	   grupoSolucion.getCantLiderProyecto() == requeridos.getCantLiderProyecto()
				&& grupoSolucion.getCantArquitectos() == requeridos.getCantArquitectos()
				&& grupoSolucion.getCantProgramadores() == requeridos.getCantProgramadores()
				&& grupoSolucion.getCantTesters() == requeridos.getCantTesters();
	}

	private boolean hayConflicto(int id, Set<Persona> personas) {
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
