package objetos;

public class Requerimiento
{
	private int cantLiderProyecto;
	private int cantArquitectos;
	private int cantDevelopers;
	private int cantTesters;
	
	public Requerimiento(int cantLiderProyecto, int cantArquitectos, int cantDevelopers, int cantTesters)
	{
		setCantLiderProyecto(cantLiderProyecto);
		setCantArquitectos(cantArquitectos);
		setCantDevelopers(cantDevelopers);
		setCantTesters(cantTesters);
	}

	public int getCantLiderProyecto()
	{
		return cantLiderProyecto;
	}
	
	public void setCantLiderProyecto(int cantLiderProyecto)
	{
		this.cantLiderProyecto = cantLiderProyecto;
	}
	
	public int getCantArquitectos()
	{
		return cantArquitectos;
	}
	
	public void setCantArquitectos(int cantArquitectos)
	{
		this.cantArquitectos = cantArquitectos;
	}
	
	public int getCantDevelopers()
	{
		return cantDevelopers;
	}
	
	public void setCantDevelopers(int cantDevelopers)
	{
		this.cantDevelopers = cantDevelopers;
	}
	
	public int getCantTesters()
	{
		return cantTesters;
	}
	
	public void setCantTesters(int cantTesters)
	{
		this.cantTesters = cantTesters;
	}
}
