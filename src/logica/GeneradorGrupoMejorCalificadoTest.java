package logica;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import objetos.Rol;

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

	@Test
	public void agregarIncompatibilidadTest() {
		generador.agregarPersona(5, "Juan Perez", Rol.PROGRAMADOR);
		generador.agregarIncompatibilidad(0, 1);
		assertTrue(generador.getIncompatibilidades(0).contains(1));
	}
	
	@Test
	public void generarMejorEquipoTest() {
		generador.agregarPersona(4, "Juan Perez", Rol.LIDER_DE_PROYECTO);
		generador.agregarPersona(5, "Roberto Gomez", Rol.LIDER_DE_PROYECTO);
		
		generador.agregarPersona(3, "Micaela Robles", Rol.ARQUITECTO);
		generador.agregarPersona(4, "Marcelo Rodriguez", Rol.ARQUITECTO);
		generador.agregarPersona(5, "Esteban Quito", Rol.ARQUITECTO);
		
		generador.agregarPersona(3, "Braian Davico", Rol.PROGRAMADOR);
		generador.agregarPersona(3, "Bruno Avila", Rol.PROGRAMADOR);
		generador.agregarPersona(3, "Norberto Beltrán", Rol.PROGRAMADOR);
		
		generador.setRequerimientos(1, 2, 2, 2);
	}
}
