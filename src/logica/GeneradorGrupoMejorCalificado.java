package logica;

import java.util.List;
import java.util.Set;
import grupoMejorCalificado.Solver;
import grupoMejorCalificado.SolverHeuristico;
import java.util.ArrayList;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class GeneradorGrupoMejorCalificado {
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Requerimiento requeridos;
	private Solver solver;

	public GeneradorGrupoMejorCalificado() {

		personas = new ArrayList<>();
		incompatibles = new ArrayList<>();
	}

	public void agregarPersona(Persona p) {
		personas.add(p);
		incompatibles.add(new ArrayList<>());
	}

	public void agregarPersona(int rendimiento, String nombre, Rol rol) {
		personas.add(new Persona(personas.size(), rendimiento, nombre, rol));
		incompatibles.add(new ArrayList<>());
	}

	public void agregarIncompatibilidad(int id, int idIncompatible) {
		if (id >= incompatibles.size())
			throw new RuntimeException("ID no válido: " + id);
		if (idIncompatible >= incompatibles.size())
			throw new RuntimeException("ID no válido: " + idIncompatible);

		incompatibles.get(id).add(idIncompatible);
		incompatibles.get(idIncompatible).add(id);
	}

	public void setRequerimientos(int cantLiderProyecto, int cantArquitectos, int cantDevelopers, int cantTesters) {
		this.requeridos = new Requerimiento(cantLiderProyecto, cantArquitectos, cantDevelopers, cantTesters);
	}

	public Set<Persona> generarMejorEquipo() {
		solver = new Solver(personas, incompatibles, requeridos);
		solver.start();
		try {
			solver.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return solver.getGrupoMayorPuntuacion().getPersonas();
	}

	public Set<Persona> generarMejorEquipoHeuristico() {
		SolverHeuristico solverH = new SolverHeuristico(personas, incompatibles, requeridos);
		solverH.start();
		try {
			solverH.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return solverH.getGrupoSolucion().getPersonas();
	}

	public List<Persona> getListaPersonas() {
		return personas;
	}

	public List<Integer> getIncompatibilidadesById(int id) {
		return incompatibles.get(id);
	}

	public List<List<Integer>> getIncompatibilidades() {
		return incompatibles;
	}

	public Requerimiento getRequerimientos() {
		return requeridos;
	}

	public int getCantRecursiones() {
		if (solver == null) {
			throw new RuntimeException("Debe cargar personas, requisitos e incompatibilidades antes.");
		}

		return solver.getCantRecursiones();
	}
}
