package interfaz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import objetos.Persona;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class EquipoIdeal implements ActionListener{

	private JFrame frame;
	private Set<Persona> grupoFuerzaBruta;
	private Set<Persona> grupoHeuristica;
	private JPanel panelPrincipal;
	private JPanel panelFuerzaBruta;
	private JPanel panelHeuristica;
	private JButton botonGuardarResultados;


	public EquipoIdeal(Set<Persona> solucionFuerzaBruta, Set<Persona> solucionHeuristica) {
		this.grupoFuerzaBruta=solucionFuerzaBruta;
		this.grupoHeuristica=solucionHeuristica;
		initialize();
	}

	private void initialize() {
		
		setupFrame();
		
		generarPaneles();
		
		generarTitulos();
		
		generarBotones();
		
		mostrarResultadoFuerzaBruta();
		
		mostrarResultadoHeuristica();
		
	}

	private void setupFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 772, 654);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Mejor Grupo");
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void generarBotones() {
		botonGuardarResultados = new JButton("Guardar Resultados");
		botonGuardarResultados.setFont(new Font("Calibri", Font.BOLD, 15));
		botonGuardarResultados.setBounds(276, 562, 192, 42);
		botonGuardarResultados.addActionListener(this);
		panelPrincipal.add(botonGuardarResultados);
		
	}

	private void generarTitulos() {
		JLabel etiquetaFuerzaBruta = new JLabel("Solucion con Fuerza Bruta:");
		etiquetaFuerzaBruta.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaFuerzaBruta.setFont(new Font("Calibri", Font.BOLD, 19));
		etiquetaFuerzaBruta.setBounds(62, 89, 254, 27);
		panelPrincipal.add(etiquetaFuerzaBruta);
		
		JLabel etiquetaHeuristica = new JLabel("Solucion con Heuristica:");
		etiquetaHeuristica.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaHeuristica.setFont(new Font("Calibri", Font.BOLD, 19));
		etiquetaHeuristica.setBounds(436, 89, 254, 27);
		panelPrincipal.add(etiquetaHeuristica);
	}

	private void generarPaneles() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 756, 615);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		panelFuerzaBruta = new JPanel(new GridLayout(0, 2));
		JScrollPane panelFuerzaBrutaDeslizable= new JScrollPane(panelFuerzaBruta);
		panelFuerzaBrutaDeslizable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelFuerzaBrutaDeslizable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelFuerzaBrutaDeslizable.setBounds(10, 127, 345, 310);
		panelPrincipal.add(panelFuerzaBrutaDeslizable);
		
	
		panelHeuristica = new JPanel(new GridLayout(0, 2));
		JScrollPane panelHeuristicaDeslizable= new JScrollPane(panelHeuristica);
		panelHeuristicaDeslizable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelHeuristicaDeslizable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelHeuristicaDeslizable.setBounds(389, 127, 357, 310);
		panelPrincipal.add(panelHeuristicaDeslizable);
	}
	
	private void mostrarResultadoFuerzaBruta() {
		for(Persona persona: grupoFuerzaBruta) {
			JLabel labelFoto= new JLabel();
			labelFoto.setIcon(persona.getFotoDePerfil());
			labelFoto.setHorizontalAlignment(JLabel.CENTER);
			JLabel labelDatos= new JLabel(persona.getNombre() + " , " + persona.getRol().toString());
			labelDatos.setHorizontalAlignment(JLabel.CENTER);
			JPanel panelParticular= new JPanel(new BorderLayout());
			panelParticular.add(labelFoto,BorderLayout.CENTER);
			panelParticular.add(labelDatos,BorderLayout.SOUTH);
			panelFuerzaBruta.add(panelParticular);
			
		}
	}
	
	private void mostrarResultadoHeuristica() {
		for(Persona persona: grupoHeuristica) {
			JLabel labelFoto= new JLabel();
			labelFoto.setIcon(persona.getFotoDePerfil());
			labelFoto.setHorizontalAlignment(JLabel.CENTER);
			JLabel labelDatos= new JLabel(persona.getNombre() + " , " + persona.getRol().toString());
			labelDatos.setHorizontalAlignment(JLabel.CENTER);
			JPanel panelParticular= new JPanel(new BorderLayout());
			panelParticular.add(labelFoto,BorderLayout.CENTER);
			panelParticular.add(labelDatos,BorderLayout.SOUTH);
			panelHeuristica.add(panelParticular);
			
		}
	}
	
	public void guardarGrupoEnArchivo(Set<Persona> grupoFuerzaBruta,Set<Persona> grupoHeuristica, String nombreArchivo) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo + ".txt"))) {
	        writer.write("Grupo Solucion (Fuerza Bruta)");
	        writer.newLine();
	    	for (Persona persona : grupoFuerzaBruta) {
	            writer.write("Nombre: " + persona.getNombre() + ", Rol: " + persona.getRol().toString() + ", Calif.Historica: " + persona.getRendimiento());
	            writer.newLine();
	        }
	    	writer.newLine();
	    	
	    	writer.write("Grupo Solucion (Heuristica)");
	        writer.newLine();
	        for (Persona persona : grupoHeuristica) {
	            writer.write("Nombre: " + persona.getNombre() + ", Rol: " + persona.getRol().toString() + ", Calif.Historica: " + persona.getRendimiento());
	            writer.newLine();
	        }
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botonGuardarResultados) {
			String resultado=JOptionPane.showInputDialog(null, "Como va a llamar al archivo?","Guardar", JOptionPane.PLAIN_MESSAGE);
			guardarGrupoEnArchivo(grupoFuerzaBruta, grupoHeuristica, resultado);
		}
		
	}
}
