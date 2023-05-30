package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import logica.GeneradorGrupoMejorCalificado;
import objetos.Persona;
import objetos.Requerimiento;
import objetos.Rol;

public class EquipoIdeal implements ActionListener{

	private JFrame frame;
	private JList<String> listaDeEmpleados;
	private JPanel panelDeDetalles;
	private JButton botonAgregar;
	private JButton botonAgregarIncompatibilidad;
	private JButton botonGenerarGrupo;
	private JButton botonListo;
	private JButton botonLimpiar;
	private JProgressBar barraDeProgreso;
	private JPanel panelDeRequerimientos;
	private JLabel etiquetaRequerimientos;
	private JSpinner spinnerLider;
	private JSpinner spinnerArquitecto;
	private JSpinner spinnerProgramador;
	private JSpinner spinnerTester;
	private GeneradorGrupoMejorCalificado grupo;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipoIdeal window = new EquipoIdeal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EquipoIdeal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setBounds(100, 100, 700, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		generarListaDeEmpleados();
		
		generarBotones();
		
		generarPanelDeDetalles();
		
		generarBarraDeProgreso();
		
		generarCuadroDeRequerimientos();
		
	}

	private void generarBarraDeProgreso() {
		barraDeProgreso = new JProgressBar();
		barraDeProgreso.setBounds(142, 639, 415, 31);
		frame.getContentPane().add(barraDeProgreso);
	}

	private void generarCuadroDeRequerimientos() {
		
		panelDeRequerimientos = new JPanel();
		panelDeRequerimientos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDeRequerimientos.setBackground(new Color(129, 146, 163));
		panelDeRequerimientos.setBounds(10, 50, 453, 113);
		frame.getContentPane().add(panelDeRequerimientos);
		panelDeRequerimientos.setLayout(null);
		
		JLabel etiquetaLider = new JLabel("Lider De Proyecto:");
		etiquetaLider.setFont(new Font("Calibri", Font.BOLD, 15));
		etiquetaLider.setBounds(10, 42, 121, 17);
		panelDeRequerimientos.add(etiquetaLider);
		
		spinnerLider = new JSpinner();
		spinnerLider.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinnerLider.setBounds(141, 38, 48, 20);
		panelDeRequerimientos.add(spinnerLider);
		spinnerLider.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		
		JLabel etiquetaArquitecto = new JLabel("Arquitecto:");
		etiquetaArquitecto.setFont(new Font("Calibri", Font.BOLD, 15));
		etiquetaArquitecto.setBounds(10, 70, 121, 27);
		panelDeRequerimientos.add(etiquetaArquitecto);
		
		spinnerArquitecto = new JSpinner();
		spinnerArquitecto.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinnerArquitecto.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerArquitecto.setBounds(141, 71, 48, 20);
		panelDeRequerimientos.add(spinnerArquitecto);
		
		JLabel etiquetaProgramador = new JLabel("Programador:");
		etiquetaProgramador.setFont(new Font("Calibri", Font.BOLD, 15));
		etiquetaProgramador.setBounds(254, 37, 94, 27);
		panelDeRequerimientos.add(etiquetaProgramador);
		
		spinnerProgramador = new JSpinner();
		spinnerProgramador.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinnerProgramador.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerProgramador.setBounds(358, 38, 52, 20);
		panelDeRequerimientos.add(spinnerProgramador);
		
		JLabel etiquetaTester = new JLabel("Tester:");
		etiquetaTester.setFont(new Font("Calibri", Font.BOLD, 15));
		etiquetaTester.setBounds(254, 73, 69, 20);
		panelDeRequerimientos.add(etiquetaTester);
		
		spinnerTester = new JSpinner();
		spinnerTester.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinnerTester.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerTester.setBounds(358, 72, 52, 17);
		panelDeRequerimientos.add(spinnerTester);
		
		etiquetaRequerimientos = new JLabel("Seleccione los requerimientos para el equipo:");
		etiquetaRequerimientos.setBounds(10, 0, 346, 31);
		panelDeRequerimientos.add(etiquetaRequerimientos);
		etiquetaRequerimientos.setBackground(new Color(255, 255, 255));
		etiquetaRequerimientos.setFont(new Font("Calibri", Font.BOLD, 18));
		
		
		
		
	}

	private void generarBotones() {
		botonGenerarGrupo =new JButton("Generar grupo");
		botonGenerarGrupo.setFont(new Font("Calibri", Font.BOLD, 15));
		botonGenerarGrupo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonGenerarGrupo.setBounds(265, 585, 163, 43);
		botonGenerarGrupo.addActionListener(this);
		frame.getContentPane().add(botonGenerarGrupo);
		
		botonAgregar = new JButton("Agregar empleado");
		botonAgregar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonAgregar.setFont(new Font("Calibri", Font.BOLD, 15));
		botonAgregar.setBounds(10, 519, 208, 23);
		botonAgregar.addActionListener(this);
		frame.getContentPane().add(botonAgregar);
		
		botonListo = new JButton("Listo!");
		botonListo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonListo.setBounds(473, 128, 89, 32);
		botonListo.addActionListener(this);
		frame.getContentPane().add(botonListo);
		botonListo.setFont(new Font("Calibri", Font.BOLD, 15));
		
		botonLimpiar = new JButton("Limpiar");
		botonLimpiar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonLimpiar.setFont(new Font("Calibri", Font.BOLD, 15));
		botonLimpiar.setBounds(473, 66, 89, 38);
		botonLimpiar.addActionListener(this);
		frame.getContentPane().add(botonLimpiar);
		
		botonAgregarIncompatibilidad = new JButton("Agregar Incompatibilidad");
		botonAgregarIncompatibilidad.addActionListener(this);
		botonAgregarIncompatibilidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonAgregarIncompatibilidad.setFont(new Font("Calibri", Font.BOLD, 15));
		botonAgregarIncompatibilidad.setBounds(10, 553, 208, 23);
		frame.getContentPane().add(botonAgregarIncompatibilidad);
	}

	private void generarPanelDeDetalles() {
		panelDeDetalles = new JPanel();
		panelDeDetalles.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDeDetalles.setBounds(403, 235, 271, 273);
		frame.getContentPane().add(panelDeDetalles);
		panelDeDetalles.setLayout(null);
		
		JLabel etiqueta = new JLabel("Detalle:");
		etiqueta.setFont(new Font("Calibri", Font.BOLD, 18));
		etiqueta.setBounds(403, 201, 89, 17);
		frame.getContentPane().add(etiqueta);
	}


	
	private void cargarEmpleado() {
		if(grupo!=null) {
			String nombre=JOptionPane.showInputDialog(null,"Ingrese el nombre del empleado/a: ","Nombre del empleado", JOptionPane.DEFAULT_OPTION);
			if(!nombre.isEmpty()) {
				int rendimiento=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la calificacion historica de " + nombre,"Rendimiento", JOptionPane.PLAIN_MESSAGE,null,new Object []{1,2,3,4,5},null).toString());
				Rol rol=(Rol) JOptionPane.showInputDialog(null,"Seleccione el rol de "+ nombre, "Rol",JOptionPane.PLAIN_MESSAGE,null,Rol.values(),null);
				grupo.agregarPersona(rendimiento, nombre, rol);
				mostrarEmpleadoEnLista(nombre, rol);
			}
			else {
				JOptionPane.showMessageDialog(null, "Ingresar un nombre es obligatorio","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Primero debe especificar los requerimientos del equipo!", "Advertencia",0);
		}
	}
	
	private void agregarIncompatibilidad() {
		if(grupo==null) {
			JOptionPane.showMessageDialog(null, "Primero debe especificar los requerimientos del equipo!", "Advertencia",0);
		}
		if(grupo.getListaPersonas().size()>=2) {
			Persona p1=(Persona) JOptionPane.showInputDialog(null, "Seleccione un empleado:", "Incompatibilidad", JOptionPane.PLAIN_MESSAGE, null, grupo.getListaPersonas().toArray(), null);
			Persona p2=(Persona) JOptionPane.showInputDialog(null, p1.getNombre() + " es incompatible con...", "Incompatibilidad", JOptionPane.PLAIN_MESSAGE, null, grupo.getListaPersonas().toArray(), null);
			if(p1.getNombre().equals(p2.getNombre())) {
				JOptionPane.showMessageDialog(null, "Un empleado no puede ser incompatible consigo mismo", "Error",0);
			}
			else {
				grupo.agregarIncompatibilidad(getIdByNombre(p1.getNombre()), getIdByNombre(p2.getNombre()));
				JOptionPane.showMessageDialog(null, "Se agrego la incompatibilidad " + p1.getNombre() + " - " + p2.getNombre(), "Exito",JOptionPane.INFORMATION_MESSAGE);
				System.out.println(grupo.getIncompatibilidades().toString());
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Se debe tener al menos 2 empleados en el grupo para agregar una incompatibilidad", "Advertencia",0);
		}
	}
	
	private void limpiarSeleccion() {
		int resultado=JOptionPane.showConfirmDialog(null, "Seguro que desea reiniciar su seleccion?", "Limpiar",0);
		if(resultado==0) {
			grupo=null;
			panelDeRequerimientos.add(spinnerLider);
			panelDeRequerimientos.add(spinnerArquitecto);
			panelDeRequerimientos.add(spinnerProgramador);
			panelDeRequerimientos.add(spinnerTester);
			botonListo.setBackground(new Color(240, 240, 240));
			botonListo.setEnabled(true);
			etiquetaRequerimientos.setText("Seleccione los requerimientos para el equipo:");
		}
	}
	
	private void confirmarRequerimiento() {
		panelDeRequerimientos.remove(spinnerLider);
		panelDeRequerimientos.remove(spinnerArquitecto);
		panelDeRequerimientos.remove(spinnerProgramador);
		panelDeRequerimientos.remove(spinnerTester);
		botonListo.setBackground(new Color(180,255,180));
		botonListo.setEnabled(false);
		etiquetaRequerimientos.setText("Requerimientos:");
		grupo= new GeneradorGrupoMejorCalificado();
		grupo.setRequerimientos((int)spinnerLider.getValue(), (int)spinnerArquitecto.getValue(), (int)spinnerProgramador.getValue(), (int)spinnerTester.getValue());
	}
		
	private int getIdByNombre(String nombre) {
		for(Persona persona: grupo.getListaPersonas()) {
			if(persona.getNombre().equals(nombre)) {
				return persona.getId();
			}
		}
		return -1;
	}
	
	
	private void generarListaDeEmpleados() {
		listaDeEmpleados = new JList<String>();
		listaDeEmpleados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listaDeEmpleados.setBounds(10, 235, 271, 273);
		frame.getContentPane().add(listaDeEmpleados);
		
		JLabel etiqueta = new JLabel("Lista de empleados:");
		etiqueta.setFont(new Font("Calibri", Font.BOLD, 18));
		etiqueta.setBounds(10, 198, 208, 23);
		frame.getContentPane().add(etiqueta);
		modelarListaEmpleados();
	}

	private DefaultListModel<String> modelarListaEmpleados() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaDeEmpleados.setModel(modelo);
		return modelo;
	}

	private DefaultListModel<String> mostrarEmpleadoEnLista(String nombre, Rol rol) {
		DefaultListModel<String> modelo = (DefaultListModel<String>) listaDeEmpleados.getModel();
		modelo.addElement(nombre + " - " + rol);
		return modelo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==botonListo) {
			confirmarRequerimiento();
		}
		
		if(e.getSource()==botonLimpiar) {
			limpiarSeleccion();
		}
		
		if(e.getSource()==botonAgregar) {
			cargarEmpleado();
		}
		
		if(e.getSource()==botonAgregarIncompatibilidad) {
			agregarIncompatibilidad();
		}
	}
}
