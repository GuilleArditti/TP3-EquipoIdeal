package objetos;

public class Requerimiento {
	private int cantLiderProyecto;
	private int cantArquitectos;
	private int cantDevelopers;
	private int cantTesters;
	private int tamano;

	public Requerimiento(int cantLiderProyecto, int cantArquitectos, int cantDevelopers, int cantTesters) {
		setCantLiderProyecto(cantLiderProyecto);
		setCantArquitectos(cantArquitectos);
		setCantDevelopers(cantDevelopers);
		setCantTesters(cantTesters);
		setTamano(cantLiderProyecto + cantArquitectos + cantDevelopers + cantTesters);
	}

	@Override
	public String toString() {
		return "Requerimientos \n Lider de Proyecto: " + cantLiderProyecto + "\n Arquitectos: " + cantArquitectos
				+ "\n Developers: " + cantDevelopers + "\n Testers: " + cantTesters + "\n Integrantes del equipo: "
				+ tamano;
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

	public int getCantDevelopers() {
		return cantDevelopers;
	}

	private void setCantDevelopers(int cantDevelopers) {
		this.cantDevelopers = cantDevelopers;
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
}
