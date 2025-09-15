package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Partido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEqLocal;
	private JTextField txtEqVisitante;
	private JTextField txtGolesLocal;
	private JTextField txtGolesVisitante;
	private JTextField txtLugar;
	private JTextField txtFecha;
	private JTable table;
	private ArrayList<Partido> partidos = new ArrayList<Partido>();
	private DefaultTableModel modelo;
	private DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEquipoLocal = new JLabel("Equipo local");
		lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEquipoLocal.setBounds(22, 40, 76, 14);
		contentPane.add(lblEquipoLocal);

		txtEqLocal = new JTextField();
		txtEqLocal.setBounds(142, 37, 132, 20);
		contentPane.add(txtEqLocal);
		txtEqLocal.setColumns(10);

		JLabel lblEquipoVisitante = new JLabel("Equipo visitante");
		lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEquipoVisitante.setBounds(22, 71, 98, 14);
		contentPane.add(lblEquipoVisitante);

		txtEqVisitante = new JTextField();
		txtEqVisitante.setColumns(10);
		txtEqVisitante.setBounds(142, 68, 132, 20);
		contentPane.add(txtEqVisitante);

		JLabel lblGolesLocal = new JLabel("Goles local");
		lblGolesLocal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGolesLocal.setBounds(22, 99, 98, 14);
		contentPane.add(lblGolesLocal);

		txtGolesLocal = new JTextField();
		txtGolesLocal.setColumns(10);
		txtGolesLocal.setBounds(142, 96, 132, 20);
		contentPane.add(txtGolesLocal);

		txtGolesVisitante = new JTextField();
		txtGolesVisitante.setColumns(10);
		txtGolesVisitante.setBounds(142, 124, 132, 20);
		contentPane.add(txtGolesVisitante);

		JLabel lblGolesVisitante = new JLabel("Goles visitante");
		lblGolesVisitante.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGolesVisitante.setBounds(22, 127, 98, 14);
		contentPane.add(lblGolesVisitante);

		txtLugar = new JTextField();
		txtLugar.setColumns(10);
		txtLugar.setBounds(142, 155, 132, 20);
		contentPane.add(txtLugar);

		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLugar.setBounds(22, 158, 98, 14);
		contentPane.add(lblLugar);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(22, 189, 98, 14);
		contentPane.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(142, 186, 132, 20);
		contentPane.add(txtFecha);

		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirPartido();
			}
		});
		btnAnadir.setBounds(198, 214, 76, 23);
		contentPane.add(btnAnadir);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPartidosBinario();
			}
		});
		btnCargar.setBounds(306, 128, 91, 23);
		contentPane.add(btnCargar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarPartidosBinario();
			}
		});
		btnGuardar.setBounds(306, 100, 91, 23);
		contentPane.add(btnGuardar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 265, 416, 165);
		contentPane.add(scrollPane);

		modelo = new DefaultTableModel(
				new String[] { "Equipo local", "Equipo visitante", "Goles local", "Goles visitante", "Lugar", "Fecha" },
				0);
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JButton btnGuardarRA = new JButton("Guardar");
		btnGuardarRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarPartidosRA();
			}
		});
		btnGuardarRA.setBounds(306, 214, 91, 23);
		contentPane.add(btnGuardarRA);
		
		JButton btnCargarRA = new JButton("Cargar ");
		btnCargarRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPartidosRA();
			}
		});
		btnCargarRA.setBounds(306, 188, 91, 23);
		contentPane.add(btnCargarRA);
		
		JLabel lblNewLabel = new JLabel("Objeto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(306, 76, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAccesoAleatorio = new JLabel("Acceso Aleatorio");
		lblAccesoAleatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccesoAleatorio.setBounds(306, 163, 116, 14);
		contentPane.add(lblAccesoAleatorio);

	}
	public void cargarPartidosBinario() {
		partidos = new ArrayList<Partido>();
		try {
			FileInputStream fi = new FileInputStream(new File("Resultados.dat"));
			ObjectInputStream dataIS = new ObjectInputStream(fi);
			while(fi.getChannel().position()<fi.getChannel().size()) {
				partidos.add((Partido) dataIS.readObject());
			}
			dataIS.close();
			JOptionPane.showMessageDialog(null, "Los partidos han sido cargados correctamente", "Carga completa (Binario)",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de clase Partido no encontrada");
		}  
		cargarTabla();
	

	}

	public void guardarPartidosBinario() {
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		try {
			fos = new FileOutputStream("Resultados.dat");
			salida = new ObjectOutputStream(fos);
			for (int i = 0; i < partidos.size(); i++) {
				salida.writeObject(partidos.get(i));
			}
			JOptionPane.showMessageDialog(null, "Los partidos se han guardado correctamente", "Guardado completo (Binario)",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero");
		} catch (IOException e) {
			System.out.println("Error al crear el fichero");
		} finally {
			try {
				if (salida != null) {
					salida.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
		}
	}
	public void cargarPartidosRA() {
		partidos = new ArrayList<Partido>();
		try {
			RandomAccessFile file = new RandomAccessFile(new File("ResultadosRA.dat"), "r");
			file.seek(0);
			while (file.getFilePointer() < file.length()) {
				char[] equipoLocalArr = new char[20];
				for (int i = 0; i < 20; i++) equipoLocalArr[i] = file.readChar();
				String equipoLocal = new String(equipoLocalArr);

				char[] equipoVisitanteArr = new char[20];
				for (int i = 0; i < 20; i++) equipoVisitanteArr[i] = file.readChar();
				String equipoVisitante = new String(equipoVisitanteArr);

				char[] lugarArr = new char[20];
				for (int i = 0; i < 20; i++) lugarArr[i] = file.readChar();
				String lugar = new String(lugarArr);

				char[] fechaArr = new char[8];
				for (int i = 0; i < 8; i++) fechaArr[i] = file.readChar();
				String fechaStr = new String(fechaArr);

				int golesLocal = file.readInt();
				int golesVisitante = file.readInt();
				Date fecha = null;
				try {
					fecha = formatoFecha.parse(fechaStr);
				} catch (ParseException e) {
					System.out.println("Error al convertir la fecha");
				}
				partidos.add(new Partido(
					equipoLocal,
					equipoVisitante,
					golesLocal,
					golesVisitante,
					lugar,
					fecha
				));
			}
			file.close();
			JOptionPane.showMessageDialog(null, "Los partidos han sido cargados correctamente", "Carga completa (RA)",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
		}
		cargarTabla();
	

	}

	public void guardarPartidosRA() {
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(new File("ResultadosRA.dat"),"rw");
			file.seek(0);
			for (int i = 0; i < partidos.size(); i++) {
				Partido partido = partidos.get(i);
				StringBuffer buffer = new StringBuffer(partido.getEquipoLocal());
				buffer.setLength(20);
				file.writeChars(buffer.toString());
				buffer = new StringBuffer(partido.getEquipoVisitante());
				buffer.setLength(20);
				file.writeChars(buffer.toString());
				buffer = new StringBuffer(partido.getLugar());
				buffer.setLength(20);
				file.writeChars(buffer.toString());
				file.writeChars(formatoFecha.format(partido.getFecha()));
				file.writeInt(partido.getGolesLocal());
				file.writeInt(partido.getGolesVisitante());
			}
			JOptionPane.showMessageDialog(null, "Los partidos se han guardado correctamente", "Guardado completo (RA)",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero");
		} catch (IOException e) {
			System.out.println("Error al crear el fichero");
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
		}
	}

	public void anadirPartido() {
		ArrayList<String> errores = new ArrayList<String>();
		// Goles local
		int golesLocal = -1;
		try {
			golesLocal = Integer.parseInt(txtGolesLocal.getText());
			if (golesLocal > 99) {
				errores.add("Goles local no puede ser superior a 99.");
			}
		} catch (NumberFormatException error) {
			errores.add("Goles local debe ser un número entero.");
		}
		// Goles visitante
		int golesVisitante = -1;
		try {
			golesVisitante = Integer.parseInt(txtGolesVisitante.getText());
			if (golesVisitante > 99) {
				errores.add("Goles visitante no puede ser superior a 99.");
			}
		} catch (NumberFormatException error) {
			errores.add("Goles visitante debe ser un número entero.");
		}
		// Fecha
		Date fecha = null;
		try {
			fecha = formatoFecha.parse(txtFecha.getText());
		} catch (ParseException e1) {
			errores.add("Fecha debe tener el formato dd/MM/yy.");
		}
		// Equipo local, visitante y lugar
		String equipoLocal = (String) txtEqLocal.getText();
		String equipoVisitante = (String) txtEqVisitante.getText();
		String lugar = (String) txtLugar.getText();

		if (!(equipoLocal.length() >= 1 && equipoLocal.length() <= 20)) {
			errores.add("Equipo Local tiene que tener entre 1 y 20 caracteres.");
		}
		if (!(equipoVisitante.length() >= 1 && equipoVisitante.length() <= 20)) {
			errores.add("Equipo Visitante tiene que tener entre 1 y 20 caracteres.");
		}
		if (!(lugar.length() >= 1 && lugar.length() <= 20)) {
			errores.add("Lugar tiene que tener entre 1 y 20 caracteres.");
		}

		if (errores.size() == 0) {
			partidos.add(new Partido(equipoLocal, equipoVisitante, golesLocal, golesVisitante, lugar, fecha));
			cargarTabla();
		} else {
			JOptionPane.showMessageDialog(null, "- " + String.join("\n- ", errores), "Error al añadir partido",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cargarTabla() {
		modelo.setRowCount(0);
		setBounds(100, 100, 464, 511);
		for (int i = 0; i < partidos.size(); i++) {
			Partido partido = partidos.get(i);
			String[] fila = new String[6];
			fila[0] = partido.getEquipoLocal();
			fila[1] = partido.getEquipoVisitante();
			fila[2] = partido.getGolesLocal() + "";
			fila[2] = partido.getGolesVisitante() + "";
			fila[4] = partido.getLugar();
			fila[5] = formatoFecha.format(partido.getFecha());
			modelo.addRow(fila);
		}

	}
}