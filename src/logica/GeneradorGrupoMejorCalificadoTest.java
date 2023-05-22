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
		fail("Not yet implemented");
	}
}
