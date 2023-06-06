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
	private SolverHeuristico solverH;
	private String tiempoFuerzaBruta;
	private String tiempoHeuristica;
	private long initialTime;

	public GeneradorGrupoMejorCalificado() {
		personas = new ArrayList<>();
		incompatibles = new ArrayList<>();
	}

	public void agregarPersona(Persona p) {
		if (!personas.contains(p)) {
			personas.add(p);
			incompatibles.add(new ArrayList<>());
		} else
			throw new IllegalArgumentException("No se permite agregar personas repetidas");
	}

	public void agregarPersona(int rendimiento, String nombre, Rol rol) {
		Persona persona = new Persona(personas.size(), rendimiento, nombre, rol);
		this.agregarPersona(persona);
	}

	public void agregarIncompatibilidad(int id, int idIncompatible) {
		validarID(id, idIncompatible);

		incompatibles.get(id).add(idIncompatible);
		incompatibles.get(idIncompatible).add(id);
	}

	public void setRequerimientos(int cantLiderProyecto, int cantArquitectos, int cantDevelopers, int cantTesters) {
		this.requeridos = new Requerimiento(cantLiderProyecto, cantArquitectos, cantDevelopers, cantTesters);
	}

	public Set<Persona> generarMejorEquipo() {
		iniciarCronometro();
		solver = new Solver(personas, incompatibles, requeridos);
		solver.start();
		try {
			solver.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("Algoritmo: ");
		guardarCronometroFuerzaBruta();
		return solver.getGrupoMayorPuntuacion().getPersonas();
	}

	public Set<Persona> generarMejorEquipoHeuristico() {
		iniciarCronometro();
		solverH = new SolverHeuristico(personas, incompatibles, requeridos);
		solverH.start();
		try {
			solverH.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("Heuristica: ");
		guardarCronometroHeuristica();
		return solverH.getGrupoSolucion().getPersonas();
	}
	
	/* Metodos auxiliares */

	public boolean cumpleRequerimientos() {
		int cantArquitectos = 0;
		int cantLiderProyecto = 0;
		int cantTesters = 0;
		int cantProgramadores = 0;

		for (Persona persona : personas) {
			switch (persona.getRol()) {
			case LIDER_DE_PROYECTO:
				cantLiderProyecto++;
				break;
			case ARQUITECTO:
				cantArquitectos++;
				break;
			case PROGRAMADOR:
				cantProgramadores++;
				break;
			case TESTER:
				cantTesters++;
				break;
			}
		}

		return requeridos.getCantArquitectos() <= cantArquitectos
				&& requeridos.getCantLiderProyecto() <= cantLiderProyecto && requeridos.getCantTesters() <= cantTesters
				&& requeridos.getCantProgramadores() <= cantProgramadores;
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

	public String getEstadisticas() {
		String cantRecursiones = "Cantidad de recursiones:" + getCantRecursiones();
		String tiempoEjecucion = "Tiempo de ejecucion:" + tiempoFuerzaBruta;
		return "Fuerza Bruta: " + "\n" + cantRecursiones + "\n" + tiempoEjecucion + "\n\n" + "Heuristica: "
				+ tiempoHeuristica;
	}

	public int getCantRecursiones() {
		if (solver == null)
			throw new RuntimeException("Debe cargar personas, requisitos e incompatibilidades antes.");

		return solver.getCantRecursiones();
	}

	private void guardarCronometroFuerzaBruta() {
		tiempoFuerzaBruta = (System.currentTimeMillis() - initialTime) / 1000.0 + " seg.";
		System.out.println((System.currentTimeMillis() - initialTime) / 1000.0 + " seg.");
	}

	private void guardarCronometroHeuristica() {
		tiempoHeuristica = (System.currentTimeMillis() - initialTime) / 1000.0 + " seg.";
		System.out.println((System.currentTimeMillis() - initialTime) / 1000.0 + " seg.");
	}

	private void iniciarCronometro() {
		initialTime = System.currentTimeMillis();
	}

	private void validarID(int id, int idIncompatible) {
		if (id >= incompatibles.size() || id < 0)
			throw new IllegalArgumentException("ID no valido: " + id);
		if (idIncompatible >= incompatibles.size() || idIncompatible < 0)
			throw new IllegalArgumentException("ID no valido: " + idIncompatible);
		if (id == idIncompatible) {
			throw new IllegalArgumentException("Una persona no puede ser incompatible consigo misma");
		}
		if (incompatibles.get(id).contains(idIncompatible) || incompatibles.get(idIncompatible).contains(id)) {
			throw new RuntimeException("La incompatibilidad ya fue agregada anteriormente");
		}
	}
}
