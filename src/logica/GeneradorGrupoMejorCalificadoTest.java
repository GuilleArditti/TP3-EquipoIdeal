package logica;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import objetos.Rol;
import objetos.Persona;

public class GeneradorGrupoMejorCalificadoTest {
	private GeneradorGrupoMejorCalificado generador;

	@Before
	public void setup() {
		generador = new GeneradorGrupoMejorCalificado();
	}

	@Test
	public void agregarPersonaTest() {
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
		generador.agregarPersona(4, "Juan Gomez", Rol.ARQUITECTO);
		assertEquals(generador.getListaPersonas().size(), 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarPersonaRepetidaTest() {
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
	}

	@Test
	public void agregarIncompatibilidadTest() {
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
		generador.agregarPersona(4, "Eusebio Zamora", Rol.TESTER);
		generador.agregarIncompatibilidad(0, 1);
		assertTrue(generador.getIncompatibilidadesById(0).contains(1));
		assertTrue(generador.getIncompatibilidadesById(1).contains(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarIncompatibilidadIncorrectaTest() {
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
		generador.agregarPersona(4, "Eusebio Zamora", Rol.TESTER);
		generador.agregarIncompatibilidad(0, 2);
		generador.agregarIncompatibilidad(-1, 2);
		generador.agregarIncompatibilidad(0, 0);
	}

	@Test(expected = RuntimeException.class)
	public void agregarIncompatibilidadRepetidaTest() {
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
		generador.agregarPersona(4, "Eusebio Zamora", Rol.TESTER);
		generador.agregarIncompatibilidad(0, 1);
		generador.agregarIncompatibilidad(1, 0);
	}

	@Test
	public void EquipoIncompletoTest() {
		generador.setRequerimientos(1, 1, 3, 2);

		generador.agregarPersona(4, "Lider1", Rol.LIDER_DE_PROYECTO);

		generador.agregarPersona(3, "Arquitecto1", Rol.ARQUITECTO);

		generador.agregarPersona(3, "Dev1", Rol.PROGRAMADOR);
		generador.agregarPersona(4, "Dev2", Rol.PROGRAMADOR);
		generador.agregarPersona(5, "Dev3", Rol.PROGRAMADOR);

		generador.agregarPersona(4, "Tester3", Rol.TESTER);
		
		assertFalse(generador.cumpleRequerimientos());
	}

	@Test
	public void generarMejorEquipoTest() {
		armarGrupo();
		int rendimientoEsperado = 32;
		int rendimientoObtenido = resultadoMejorEquipo();
		assertEquals(rendimientoEsperado, rendimientoObtenido);
		System.out.println("Cantidad de llamadas recursivas: " + generador.getCantRecursiones());
	}

	@Test
	public void generarMejorEquipoHeuristicoTest() {
		int rendimientoEsperado = 32;
		int rendimientoObtenido = 0;

		armarGrupo();
		Set<Persona> resultado = generador.generarMejorEquipoHeuristico();

		rendimientoObtenido = calcularRendimiento(rendimientoObtenido, resultado);

		assertEquals(rendimientoEsperado, rendimientoObtenido);
	}

	/* Metodos auxiliares */
	
	private int calcularRendimiento(int rendimientoObtenido, Set<Persona> resultado) {
		for (Persona persona : resultado)
			rendimientoObtenido += persona.getRendimiento();
		return rendimientoObtenido;
	}

	private int resultadoMejorEquipo() {
		Set<Persona> resultado = generador.generarMejorEquipo();

		int rendimientoGlobal = 0;

		rendimientoGlobal = calcularRendimiento(rendimientoGlobal, resultado);

		return rendimientoGlobal;
	}

	private void armarGrupo() {
		generador.agregarPersona(4, "Lider1", Rol.LIDER_DE_PROYECTO);
		generador.agregarPersona(5, "Lider2", Rol.LIDER_DE_PROYECTO);

		generador.agregarPersona(3, "Arquitecto1", Rol.ARQUITECTO);
		generador.agregarPersona(4, "Arquitecto2", Rol.ARQUITECTO);
		generador.agregarPersona(5, "Arquitecto3", Rol.ARQUITECTO);

		generador.agregarPersona(3, "Dev1", Rol.PROGRAMADOR);
		generador.agregarPersona(4, "Dev2", Rol.PROGRAMADOR);
		generador.agregarPersona(5, "Dev3", Rol.PROGRAMADOR);

		generador.agregarPersona(5, "Tester1", Rol.TESTER);
		generador.agregarPersona(5, "Tester2", Rol.TESTER);
		generador.agregarPersona(4, "Tester3", Rol.TESTER);

		generador.agregarIncompatibilidad(8, 9);

		generador.setRequerimientos(1, 2, 2, 2);
	}
}
