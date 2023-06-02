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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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

public class EquipoIdeal implements ActionListener, ListSelectionListener {

	private JFrame frame;
	private JList<Persona> listaDeEmpleados;
	private JPanel panelDeDetalles;
	private JLabel labelFoto;
	private JLabel etiquetaRendimiento;
	private JLabel etiquetaNombre;
	private JLabel etiquetaRol;
	private JButton botonVerIncompatibilidad;
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
	private GeneradorGrupoMejorCalificado generador;

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
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setBounds(100, 100, 700, 757);
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
		barraDeProgreso.setBounds(147, 676, 415, 31);
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

		JButton btnNewButton = new JButton("Ver m\u00E9tricas");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton.setBounds(510, 602, 139, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ver grupo");
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton_1.setBounds(510, 642, 139, 23);
		frame.getContentPane().add(btnNewButton_1);

	}

	private void generarBotones() {
		botonGenerarGrupo = new JButton("Generar grupo");
		botonGenerarGrupo.setFont(new Font("Calibri", Font.BOLD, 15));
		botonGenerarGrupo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		botonGenerarGrupo.setBounds(233, 602, 229, 43);
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

		labelFoto = new JLabel();
		labelFoto.setBounds(10, 11, 100, 100);
		panelDeDetalles.add(labelFoto);

		etiquetaRendimiento = new JLabel("");
		etiquetaRendimiento.setBounds(10, 166, 198, 25);
		panelDeDetalles.add(etiquetaRendimiento);

		etiquetaNombre = new JLabel("");
		etiquetaNombre.setBounds(120, 11, 141, 25);
		panelDeDetalles.add(etiquetaNombre);

		etiquetaRol = new JLabel("");
		etiquetaRol.setBounds(10, 130, 198, 25);
		panelDeDetalles.add(etiquetaRol);

		botonVerIncompatibilidad = new JButton("Ver incompatibilidad");
		botonVerIncompatibilidad.setFont(new Font("Calibri", Font.BOLD, 15));
		botonVerIncompatibilidad.setBounds(10, 239, 185, 23);
		botonVerIncompatibilidad.setEnabled(false);
		botonVerIncompatibilidad.addActionListener(this);
		panelDeDetalles.add(botonVerIncompatibilidad);

		JLabel etiqueta = new JLabel("Detalle:");
		etiqueta.setFont(new Font("Calibri", Font.BOLD, 18));
		etiqueta.setBounds(403, 201, 89, 17);
		frame.getContentPane().add(etiqueta);
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
				generador.agregarPersona(persona);
				mostrarEmpleadoEnLista(persona);
			} else {
				JOptionPane.showMessageDialog(null, "Ingresar un nombre es obligatorio", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Primero debe especificar los requerimientos del equipo!",
					"Advertencia", 0);
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
					System.out.println(generador.getIncompatibilidades().toString());
				} else {
					JOptionPane.showMessageDialog(null, "La incompatibilidad " + p1.getNombre() + " - " + p2.getNombre()
							+ " ya fue agregada anteriormente!", "Error", 0);
				}

			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Se debe tener al menos 2 empleados en el grupo para agregar una incompatibilidad", "Advertencia",
					0);
		}
	}

	private void limpiarSeleccion() {
		int resultado = JOptionPane.showConfirmDialog(null, "Seguro que desea reiniciar su seleccion?", "Limpiar", 0);
		if (resultado == 0) {
			generador = null;
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
		botonListo.setBackground(new Color(180, 255, 180));
		botonListo.setEnabled(false);
		etiquetaRequerimientos.setText("Requerimientos:");
		generador = new GeneradorGrupoMejorCalificado();
		generador.setRequerimientos((int) spinnerLider.getValue(), (int) spinnerArquitecto.getValue(),
				(int) spinnerProgramador.getValue(), (int) spinnerTester.getValue());
	}

	private void generarListaDeEmpleados() {
		listaDeEmpleados = new JList<Persona>();
		listaDeEmpleados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listaDeEmpleados.setBounds(10, 235, 271, 273);
		listaDeEmpleados.addListSelectionListener(this);
		frame.getContentPane().add(listaDeEmpleados);

		JLabel etiqueta = new JLabel("Lista de empleados:");
		etiqueta.setFont(new Font("Calibri", Font.BOLD, 18));
		etiqueta.setBounds(10, 198, 208, 23);
		frame.getContentPane().add(etiqueta);
		modelarListaEmpleados();
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
			ImageIcon imageIcon = new ImageIcon("FotosEmpleados/fotoPorDefault.png");
			Image image = imageIcon.getImage();
			Image imagenRedimensionada = image.getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(),
					Image.SCALE_DEFAULT);
			ImageIcon iconredimensionado = new ImageIcon(imagenRedimensionada);
			p.setFotoDePerfil(iconredimensionado);
		}
	}

	private void mostrarSeleccion() {
		etiquetaRendimiento
				.setText("Calif.Hist�rica: " + String.valueOf(listaDeEmpleados.getSelectedValue().getRendimiento()));
		etiquetaNombre.setText(listaDeEmpleados.getSelectedValue().getNombre());
		etiquetaRol.setText("Rol actual: " + listaDeEmpleados.getSelectedValue().getRol().toString());
		botonVerIncompatibilidad.setEnabled(true);
		labelFoto.setIcon(listaDeEmpleados.getSelectedValue().getFotoDePerfil());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botonListo) {
			confirmarRequerimiento();
		}

		if (e.getSource() == botonLimpiar) {
			limpiarSeleccion();
		}

		if (e.getSource() == botonAgregar) {
			cargarEmpleado();
		}

		if (e.getSource() == botonAgregarIncompatibilidad) {
			agregarIncompatibilidad();
		}

		if (e.getSource() == botonVerIncompatibilidad) {
			mostrarIncompatibilidades(listaDeEmpleados.getSelectedValue().getId());
		}
		if (e.getSource() == botonGenerarGrupo) {

		}
	}

	private void mostrarIncompatibilidades(int id) {
		JOptionPane.showMessageDialog(null, personasIncompatiblesDeUnId(id),
				getpersonaById(id).getNombre() + " es incompatible con: ", JOptionPane.PLAIN_MESSAGE);
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
	public void valueChanged(ListSelectionEvent e) {
		mostrarSeleccion();
	}
}
