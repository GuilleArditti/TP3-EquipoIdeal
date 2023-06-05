package objetos;

import java.util.Set;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import logica.GeneradorGrupoMejorCalificado;

public class SimulacionFuerzaBruta extends SwingWorker<Set<Persona>, Long>{
	
	private Set<Persona> resultadoFuerzaBruta;
	private JProgressBar barraDeProgreso;
	private GeneradorGrupoMejorCalificado generador;
	
	
	public SimulacionFuerzaBruta(JProgressBar barraDeProgreso, GeneradorGrupoMejorCalificado generador) {
		this.barraDeProgreso=barraDeProgreso;
		this.generador=generador;
	}


	@Override
	protected Set<Persona> doInBackground() throws Exception {
		resultadoFuerzaBruta=generador.generarMejorEquipo();
		barraDeProgreso.setMinimum(1);
		barraDeProgreso.setMaximum((int)generador.getCantRecursiones());
		barraDeProgreso.setStringPainted(true);
		
		for(int i=2;i<= generador.getCantRecursiones();i++) {
			barraDeProgreso.setValue(i);
			if(barraDeProgreso.getValue()<barraDeProgreso.getMaximum()) {
				
			}
		}
		
		return resultadoFuerzaBruta;
	}
	

}
