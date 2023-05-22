package logica;

import java.util.List;
import java.util.Set;
import grupoMejorCalificado.Solver;
import java.util.ArrayList;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class GeneradorGrupoMejorCalificado
{
	private List<Persona> personas;
	private List<List<Integer>> incompatibles;
	private Requerimiento requeridos;
	
	public GeneradorGrupoMejorCalificado()
	{
		personas = new ArrayList<>();
		incompatibles = new ArrayList<>();
	}
	
	public void agregarPersona(int rendimiento, String nombre, Rol rol)
	{
		personas.add(new Persona(personas.size(), rendimiento, nombre, rol));
		incompatibles.add(new ArrayList<>());
	}
	
	public void agregarIncompatibilidad(int id, int idIncompatible)
	{
		incompatibles.get(id).add(idIncompatible);
	}
	
	public void setRequerimientos(int cantLiderProyecto, int cantArquitectos,
										int cantDevelopers, int cantTesters)
	{
		this.requeridos = new Requerimiento(cantLiderProyecto, cantArquitectos,
												cantDevelopers, cantTesters);
	}
	
	public Set<Persona> generarMejorEquipo()
	{
		Solver solver = new Solver(personas, incompatibles, requeridos);
		new Thread(solver).start();
		
		return solver.getMayorPuntuacion().getPersonas();
	}
	
	public List<Persona> getListaPersonas()
	{
		return personas;
	}
	
	public List<Integer> getIncompatibilidades(int id)
	{
		return incompatibles.get(id);
	}
	
	public Requerimiento getRequerimientos()
	{
		return requeridos;
	}
}
