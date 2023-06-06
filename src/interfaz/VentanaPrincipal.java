package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFileChooser;

import logica.GeneradorGrupoMejorCalificado;
import objetos.Persona;
import objetos.Rol;
import objetos.SimulacionFuerzaBruta;

public class VentanaPrincipal implements ActionListener, ListSelectionListener {

	private JFrame frame;
	private JList<Persona> listaDeEmpleados;
	private JPanel panelDetalles;
	private JLabel labelFoto;
	private JLabel etiquetaRendimiento;
	private JLabel etiquetaNombre;
	private JLabel etiquetaRol;
	private JButton botonListo;
	private JButton botonVerIncompatibilidad;
	private JButton botonAgregarEmpleado;
	private JButton botonAgregarIncompatibilidad;
	private JButton botonGenerarGrupo;
	private JButton botonEstablecer;
	private JButton botonVerEquipo;
	private JButton botonEstadisticas;
	private JProgressBar barraDeProgreso;
	private JPanel panelDeRequerimientos;
	private JLabel etiquetaRequerimientos;
	private JSpinner spinnerLider;
	private JSpinner spinnerArquitecto;
	private JSpinner spinnerProgramador;
	private JSpinner spinnerTester;
	private GeneradorGrupoMejorCalificado generador;
	private SimulacionFuerzaBruta simulacionFuerzaBruta;
	private Set<Persona> solucionFuerzaBruta;
	private Set<Persona> solucionHeuristica;
	
	
	public VentanaPrincipal() {
		initialize();
	}

	private void initialize() {

		setupFrame();

		generarCuadroDeRequerimientos();
		
		generarListaDeEmpleados();
		
		generarPanelDeDetalles();

		generarBotones();

		generarBarraDeProgreso();

	}

	private void setupFrame() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Equipo Ideal");
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setBounds(100, 100, 630, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
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
	
	
	private void generarListaDeEmpleados() {
		listaDeEmpleados = new JList<Persona>();
		listaDeEmpleados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JScrollPane listaDeEmpleadosDeslizable= new JScrollPane(listaDeEmpleados);
		listaDeEmpleadosDeslizable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaDeEmpleadosDeslizable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		listaDeEmpleadosDeslizable.setBounds(10, 235, 229, 273);
		listaDeEmpleados.addListSelectionListener(this);
		frame.getContentPane().add(listaDeEmpleadosDeslizable);

		JLabel etiqueta = new JLabel("Lista de empleados:");
		etiqueta.setFont(new Font("Calibri", Font.BOLD, 18));
		etiqueta.setBounds(10, 198, 208, 23);
		frame.getContentPane().add(etiqueta);
		modelarListaEmpleados();
	}

	private void generarPanelDeDetalles() {
		panelDetalles = new JPanel();
		panelDetalles.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDetalles.setBounds(375, 235, 229, 273);
		frame.getContentPane().add(panelDetalles);
		panelDetalles.setLayout(null);

		labelFoto = new JLabel();
		labelFoto.setBounds(10, 11, 100, 100);
		panelDetalles.add(labelFoto);

		etiquetaRendimiento = new JLabel("");
		etiquetaRendimiento.setBounds(10, 166, 198, 25);
		panelDetalles.add(etiquetaRendimiento);

		etiquetaNombre = new JLabel("");
		etiquetaNombre.setBounds(120, 11, 141, 25);
		panelDetalles.add(etiquetaNombre);

		etiquetaRol = new JLabel("");
		etiquetaRol.setBounds(10, 130, 198, 25);
		panelDetalles.add(etiquetaRol);
		
		botonVerIncompatibilidad = new JButton("Ver incompatibilidad");
		botonVerIncompatibilidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonVerIncompatibilidad.setBounds(10, 239, 185, 23);
		panelDetalles.add(botonVerIncompatibilidad);
		botonVerIncompatibilidad.setFont(new Font("Calibri", Font.BOLD, 15));
		botonVerIncompatibilidad.setEnabled(false);
		botonVerIncompatibilidad.addActionListener(this);

		JLabel etiqueta = new JLabel("Detalle:");
		etiqueta.setFont(new Font("Calibri", Font.BOLD, 18));
		etiqueta.setBounds(375, 201, 89, 17);
		frame.getContentPane().add(etiqueta);
	}
	
	private void generarBotones() {
		botonGenerarGrupo = new JButton("Generar grupo");
		botonGenerarGrupo.setEnabled(false);
		botonGenerarGrupo.setFont(new Font("Calibri", Font.BOLD, 15));
		botonGenerarGrupo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonGenerarGrupo.setBounds(212, 626, 200, 38);
		botonGenerarGrupo.addActionListener(this);
		frame.getContentPane().add(botonGenerarGrupo);

		botonAgregarEmpleado = new JButton("Agregar empleado");
		botonAgregarEmpleado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonAgregarEmpleado.setFont(new Font("Calibri", Font.BOLD, 15));
		botonAgregarEmpleado.setBounds(10, 519, 154, 23);
		botonAgregarEmpleado.addActionListener(this);
		frame.getContentPane().add(botonAgregarEmpleado);

		botonEstablecer = new JButton("Establecer");
		botonEstablecer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonEstablecer.setBounds(494, 79, 89, 57);
		botonEstablecer.addActionListener(this);
		frame.getContentPane().add(botonEstablecer);
		botonEstablecer.setFont(new Font("Calibri", Font.BOLD, 15));

		botonAgregarIncompatibilidad = new JButton("Agregar Incompatibilidad");
		botonAgregarIncompatibilidad.addActionListener(this);
		botonAgregarIncompatibilidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonAgregarIncompatibilidad.setFont(new Font("Calibri", Font.BOLD, 15));
		botonAgregarIncompatibilidad.setBounds(10, 559, 193, 23);
		frame.getContentPane().add(botonAgregarIncompatibilidad);
		
		botonVerEquipo = new JButton("Ver equipo!");
		botonVerEquipo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonVerEquipo.setActionCommand("\r\nVer equipo!");
		botonVerEquipo.addActionListener(this);
		botonVerEquipo.setFont(new Font("Calibri", Font.BOLD, 15));
		botonVerEquipo.setBounds(153, 731, 139, 23);
		botonVerEquipo.setEnabled(false);
		frame.getContentPane().add(botonVerEquipo);
		
		botonListo = new JButton("Listo");
		botonListo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonListo.setFont(new Font("Calibri", Font.BOLD, 15));
		botonListo.setBounds(264, 476, 89, 32);
		botonListo.addActionListener(this);
		frame.getContentPane().add(botonListo);
		
		botonEstadisticas = new JButton("Estadisticas");
		botonEstadisticas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonEstadisticas.setFont(new Font("Calibri", Font.BOLD, 15));
		botonEstadisticas.setEnabled(false);
		botonEstadisticas.addActionListener(this);
		botonEstadisticas.setBounds(348, 731, 115, 23);
		frame.getContentPane().add(botonEstadisticas);
	}
	
	private void generarBarraDeProgreso() {
		barraDeProgreso = new JProgressBar();	
		barraDeProgreso.setEnabled(false);
		barraDeProgreso.setBounds(153, 686, 310, 23);
		frame.getContentPane().add(barraDeProgreso);
	}
	
	private void confirmarRequerimiento() {
		int resultado = JOptionPane.showConfirmDialog(null, "Confirma armado del equipo con: \n"
						+ spinnerLider.getValue() + " Lideres \n" + spinnerArquitecto.getValue() + " Arquitectos \n" 
						+ spinnerProgramador.getValue() + " Programadores \n" +spinnerTester.getValue() + " Testers?", "Confirmar", 0);
		if (resultado == 0) {
			generador = new GeneradorGrupoMejorCalificado();
			generador.setRequerimientos((int) spinnerLider.getValue(), (int) spinnerArquitecto.getValue(),
					(int) spinnerProgramador.getValue(), (int) spinnerTester.getValue());
			panelDeRequerimientos.remove(spinnerLider);
			panelDeRequerimientos.remove(spinnerArquitecto);
			panelDeRequerimientos.remove(spinnerProgramador);
			panelDeRequerimientos.remove(spinnerTester);
			botonEstablecer.setBackground(new Color(180, 255, 180));
			botonEstablecer.setEnabled(false);
			etiquetaRequerimientos.setText("Requerimientos:");	
		}
	}

	private void cargarEmpleado() {
		if (generador != null) {
			String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del empleado/a: ",
					"Nombre del empleado", JOptionPane.DEFAULT_OPTION);
			if (!nombre.isEmpty()) {
				int rendimiento = Integer
						.parseInt(JOptionPane
								.showInputDialog(null, "Ingrese la calificacion historica de " + nombre, "Rendimiento",
										JOptionPane.PLAIN_MESSAGE, null, new Object[] { 1, 2, 3, 4, 5 }, null)
								.toString());
				Rol rol = (Rol) JOptionPane.showInputDialog(null, "Seleccione el rol de " + nombre, "Rol",
						JOptionPane.PLAIN_MESSAGE, null, Rol.values(), null);
				Persona persona = new Persona(generador.getListaPersonas().size(), rendimiento, nombre, rol);
				elegirFoto(persona);
				try {
					generador.agregarPersona(persona);
					mostrarEmpleadoEnLista(persona);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
					persona=null;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Ingresar un nombre es obligatorio", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Primero debe especificar los requerimientos del equipo!",
					"Advertencia", 0);
		}
	}
	
	private void elegirFoto(Persona p) {
		int entrada = JOptionPane.showConfirmDialog(null, "Desea agregar una foto para " + p.getNombre() + "?",
				"Subir foto", 0);
		if (entrada == 0) {
			JFileChooser fileChooser = new JFileChooser();
			String projectFolderPath = "FotosEmpleados";
			File initialDirectory = new File(projectFolderPath);
			fileChooser.setCurrentDirectory(initialDirectory);
			ImageIcon iconRedimensionado = null;
			int resultado = fileChooser.showOpenDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
				Image image = imageIcon.getImage();
				Image imagenRedimensionada = image.getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(),
						Image.SCALE_DEFAULT);
				iconRedimensionado = new ImageIcon(imagenRedimensionada);
				p.setFotoDePerfil(iconRedimensionado);
				JOptionPane.showMessageDialog(null, iconRedimensionado, "Imagen seleccionada:",
						JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			Image image = new ImageIcon("FotosEmpleados/fotoPorDefault.png").getImage();
			ImageIcon iconredimensionado = new ImageIcon(image.getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(),
					Image.SCALE_DEFAULT));
			p.setFotoDePerfil(iconredimensionado);
		}
	}

	private void agregarIncompatibilidad() {
		if (generador == null) {
			JOptionPane.showMessageDialog(null, "Primero debe especificar los requerimientos del equipo!",
					"Advertencia", 0);
		}
		if (generador.getListaPersonas().size() >= 2) {
			Persona p1 = (Persona) JOptionPane.showInputDialog(null, "Seleccione un empleado:", "Incompatibilidad",
					JOptionPane.PLAIN_MESSAGE, null, generador.getListaPersonas().toArray(), null);
			Persona p2 = (Persona) JOptionPane.showInputDialog(null, p1.getNombre() + " es incompatible con...",
					"Incompatibilidad", JOptionPane.PLAIN_MESSAGE, null, generador.getListaPersonas().toArray(), null);
			if (p1.getNombre().equals(p2.getNombre())) {
				JOptionPane.showMessageDialog(null, "Un empleado no puede ser incompatible consigo mismo", "Error", 0);
			} else {
				if (!generador.getIncompatibilidadesById(p1.getId()).contains(p2.getId())) {
					generador.agregarIncompatibilidad(getIdByNombre(p1.getNombre()), getIdByNombre(p2.getNombre()));
					JOptionPane.showMessageDialog(null,
							"Se agrego la incompatibilidad " + p1.getNombre() + " - " + p2.getNombre(), "Exito",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "La incompatibilidad " + p1.getNombre() + " - " + p2.getNombre()
							+ " ya fue agregada anteriormente!", "Error", 0);
				}

			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Se debe tener al menos 2 empleados en la lista para agregar una incompatibilidad", "Advertencia",
					0);
		}
	}

	private DefaultListModel<Persona> modelarListaEmpleados() {
		DefaultListModel<Persona> modelo = new DefaultListModel<>();
		listaDeEmpleados.setModel(modelo);
		return modelo;
	}

	private DefaultListModel<Persona> mostrarEmpleadoEnLista(Persona persona) {
		DefaultListModel<Persona> modelo = (DefaultListModel<Persona>) listaDeEmpleados.getModel();
		modelo.addElement(persona);
		return modelo;
	}

	private void mostrarSeleccion() {
		etiquetaRendimiento.setText("Calif.Historica: " + String.valueOf(listaDeEmpleados.getSelectedValue().getRendimiento()));
		etiquetaNombre.setText(listaDeEmpleados.getSelectedValue().getNombre());
		etiquetaRol.setText("Rol actual: " + listaDeEmpleados.getSelectedValue().getRol().toString());
		botonVerIncompatibilidad.setEnabled(true);
		labelFoto.setIcon(listaDeEmpleados.getSelectedValue().getFotoDePerfil());
	}
	
	private void mostrarIncompatibilidades(int id) {
		if(generador.getIncompatibilidadesById(id).isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"El empleado no tiene incompatibilidades", "Informaci�n",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, personasIncompatiblesDeUnId(id),
					getpersonaById(id).getNombre() + " es incompatible con: ", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	private String personasIncompatiblesDeUnId(int id) {
		String incompatibles = "";
		List<Integer> IDs = generador.getIncompatibilidadesById(id);
		for (int identificacion : IDs) {
			incompatibles = incompatibles + getpersonaById(identificacion).toString() + "\n";
		}

		return incompatibles;
	}

	private int getIdByNombre(String nombre) {
		for (Persona persona : generador.getListaPersonas()) {
			if (persona.getNombre().equals(nombre)) {
				return persona.getId();
			}
		}
		return -1;
	}

	private Persona getpersonaById(int id) {
		for (Persona persona : generador.getListaPersonas()) {
			if (persona.getId() == id) {
				return persona;
			}
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == botonEstablecer) {
			confirmarRequerimiento();
		}
		
		if (e.getSource() == botonAgregarEmpleado) {
			cargarEmpleado();
		}

		if (e.getSource() == botonAgregarIncompatibilidad) {
			agregarIncompatibilidad();
		}

		if (e.getSource() == botonVerIncompatibilidad) {
			mostrarIncompatibilidades(listaDeEmpleados.getSelectedValue().getId());
		}
		
		if(e.getSource()==botonListo) {
			chequearRequisitos();
		}
		
		if (e.getSource() == botonGenerarGrupo) {
			generarSolucion();
		}
		
		if(e.getSource()==botonVerEquipo) {
			@SuppressWarnings("unused")
			EquipoIdeal ventana= new EquipoIdeal(solucionFuerzaBruta,solucionHeuristica);
		}
		
		if(e.getSource()==botonEstadisticas) {
			JOptionPane.showMessageDialog(null, generador.getEstadisticas(), "Estadisticas", JOptionPane.PLAIN_MESSAGE, null);
		}
	}

	private void chequearRequisitos() {
		if (generador == null) {
			JOptionPane.showMessageDialog(null, "Primero debe especificar los requerimientos del equipo!",
					"Advertencia", 0);
		}
		if (!generador.cumpleRequerimientos()) {
			JOptionPane.showMessageDialog(null, "Aun hay roles sin cubrir! Revise la planilla de empleados",
					"Advertencia", 0);	
		}
		else {
			int resultado = JOptionPane.showConfirmDialog(null, "Termino de ingresar a los empleados?", "Confirmar lista", 0);
			if (resultado == 0) {
				cambiarEstadoBotones();
			}
		}
	}
		
	private void cambiarEstadoBotones() {
		botonEstablecer.setEnabled(false);
		botonAgregarEmpleado.setEnabled(false);
		botonAgregarIncompatibilidad.setEnabled(false);
		botonListo.setEnabled(false);
		botonGenerarGrupo.setEnabled(true);
	}

	private void generarSolucion() {
		simulacionFuerzaBruta= new SimulacionFuerzaBruta(barraDeProgreso, generador);
		simulacionFuerzaBruta.execute();
		try {
			solucionFuerzaBruta=simulacionFuerzaBruta.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		solucionHeuristica=generador.generarMejorEquipoHeuristico();
		botonGenerarGrupo.setEnabled(false);
		botonVerEquipo.setEnabled(true);
		botonEstadisticas.setEnabled(true);
	}
			

	@Override
	public void valueChanged(ListSelectionEvent e) {
		mostrarSeleccion();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
