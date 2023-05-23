package objetos;

import java.util.Set;
import java.util.HashSet;

public class Grupo {
	private int cantLiderProyecto;
	private int cantArquitectos;
	private int cantDevelopers;
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
			cantDevelopers++;
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
		case ARQUITECTO:
			cantArquitectos--;
		case PROGRAMADOR:
			cantDevelopers--;
		case TESTER:
			cantTesters--;
		}
	}
	
	public void clonar(Grupo otro)
	{
		setCantLiderProyecto(0);
		setCantArquitectos(0);
		setCantDevelopers(0);
		setCantTesters(0);
		setTamano(0);
		setPuntuacion(0);
		setPersonas(new HashSet<>());
		
		for (Persona persona : otro.personas)
		{
			this.agregar(persona);
		}
	}
	
	@Override
	public String toString()
	{
		return "Grupo [tamano=" + tamano + ", puntuacion=" + puntuacion + ",\n personas=" + personas + "]\n";
	}

	public int getCantLiderProyecto() {
		return cantLiderProyecto;
	}

	public void setCantLiderProyecto(int cantLiderProyecto) {
		this.cantLiderProyecto = cantLiderProyecto;
	}

	public int getCantArquitectos() {
		return cantArquitectos;
	}

	public void setCantArquitectos(int cantArquitectos) {
		this.cantArquitectos = cantArquitectos;
	}

	public int getCantDevelopers() {
		return cantDevelopers;
	}

	public void setCantDevelopers(int cantDevelopers) {
		this.cantDevelopers = cantDevelopers;
	}

	public int getCantTesters() {
		return cantTesters;
	}

	public void setCantTesters(int cantTesters) {
		this.cantTesters = cantTesters;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamanoGrupo) {
		this.tamano = tamanoGrupo;
	}

	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}
