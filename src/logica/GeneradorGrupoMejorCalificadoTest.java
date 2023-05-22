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
		assertEquals(generador.getListaPersonas().size(), 1);
	}

	@Test
	public void agregarIncompatibilidadTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void agregarRequerimientosTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void generarMejorEquipoTest() {
		fail("Not yet implemented");
	}
}
