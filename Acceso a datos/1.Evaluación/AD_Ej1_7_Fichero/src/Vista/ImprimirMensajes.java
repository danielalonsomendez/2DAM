package Vista;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador;
import Modelo.Mensaje;

public class ImprimirMensajes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private JFrame menu;
	private Controlador controlador;

	/**
	 * Create the frame.
	 */
	public ImprimirMensajes(JFrame Menu, Controlador Controlador) {
		menu = Menu;
		controlador = Controlador;

		setTitle("Mensajes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 529, 194);
		contentPane.add(scrollPane);

		table = new JTable();
		modelo = new DefaultTableModel(new String[] { "De", "Para", "Fecha", "Hora", "Asunto", "Contenido" }, 0);
		table = new JTable(modelo);
		table.setBounds(10, 23, 529, 194);
		scrollPane.setViewportView(table);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				menu.setVisible(true);
			}
		});
		cargarTabla();
	}

	public void cargarTabla() {
		DateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd"); // Formato fecha
		DateFormat formatHora = new SimpleDateFormat("HH:mm"); // Formato hora

		ArrayList<Mensaje> mensajes = controlador.todoslosMensajes();
		for (int i = 0; i < mensajes.size(); i++) {
			Mensaje mensaje = mensajes.get(i);
			String[] fila = new String[6];
			fila[0] = mensaje.getDe();
			fila[1] = mensaje.getPara();
			fila[2] = formatFecha.format(mensaje.getFecha());
			fila[3] = formatHora.format(mensaje.getFecha());
			fila[4] = mensaje.getAsunto();
			fila[5] = mensaje.getContenido();
			modelo.addRow(fila);
		}

	}

}
