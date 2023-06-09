package objetos;

import java.util.Set;
import java.util.HashSet;

public class Grupo {
	private int cantLiderProyecto;
	private int cantArquitectos;
	private int cantProgramadores;
	private int cantTesters;
	private int tamano;
	private int puntuacion;
	private Set<Persona> personas;

	public Grupo() {
		personas = new HashSet<>();
	}

	public void agregar(Persona persona) {
		personas.add(persona);
		tamano++;
		setPuntuacion(getPuntuacion() + persona.getRendimiento());

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

	public void quitar(Persona persona) {
		personas.remove(persona);
		tamano--;
		setPuntuacion(getPuntuacion() - persona.getRendimiento());

		switch (persona.getRol()) {
		case LIDER_DE_PROYECTO:
			cantLiderProyecto--;
			break;
		case ARQUITECTO:
			cantArquitectos--;
			break;
		case PROGRAMADOR:
			cantProgramadores--;
			break;
		case TESTER:
			cantTesters--;
			break;
		}
	}

	public void clonar(Grupo otro) {
		setCantLiderProyecto(0);
		setCantArquitectos(0);
		setCantProgramadores(0);
		setCantTesters(0);
		setTamano(0);
		setPuntuacion(0);
		setPersonas(new HashSet<>());

		for (Persona persona : otro.personas) {
			this.agregar(persona);
		}
	}

	@Override
	public String toString() {
		return "Grupo [tamano=" + tamano + ", puntuacion=" + puntuacion + ",\n personas=" + personas + "]\n"
				+ " Lider de Proyecto: " + cantLiderProyecto + "\n" + " Arquitectos: " + cantArquitectos + "\n"
				+ " Programadores: " + cantProgramadores + "\n" + " Testers: " + cantTesters + "\n";
	}

	public int getCantLiderProyecto() {
		return cantLiderProyecto;
	}

	private void setCantLiderProyecto(int cantLiderProyecto) {
		this.cantLiderProyecto = cantLiderProyecto;
	}

	public int getCantArquitectos() {
		return cantArquitectos;
	}

	private void setCantArquitectos(int cantArquitectos) {
		this.cantArquitectos = cantArquitectos;
	}

	public int getCantProgramadores() {
		return cantProgramadores;
	}

	private void setCantProgramadores(int cantProgramadores) {
		this.cantProgramadores = cantProgramadores;
	}

	public int getCantTesters() {
		return cantTesters;
	}

	private void setCantTesters(int cantTesters) {
		this.cantTesters = cantTesters;
	}

	public int getTamano() {
		return tamano;
	}

	private void setTamano(int tamanoGrupo) {
		this.tamano = tamanoGrupo;
	}

	public Set<Persona> getPersonas() {
		return personas;
	}

	private void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	private void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}
