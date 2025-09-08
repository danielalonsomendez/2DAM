package Vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class AnadirMensaje extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDia;
	private JTextField textFieldDe;
	private JTextField textFieldPara;
	private JTextField textFieldAsunto;
	private JTextField textFieldContenido;
	private JComboBox<String> comboBoxMes;
	private JComboBox<Integer> comboBoxAno;
	private JComboBox<Integer> comboBoxHora;
	private JComboBox<Integer> comboBoxMinuto;
	private JFrame menu;
	private Controlador controlador;

	/**
	 * Create the frame.
	 */
	public AnadirMensaje(JFrame Menu, Controlador Controlador) {
		menu = Menu;
		controlador = Controlador;

		setTitle("Añadir Mensaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 26, 46, 14);
		contentPane.add(lblFecha);

		textFieldDia = new JTextField();
		textFieldDia.setBounds(62, 23, 46, 22);
		contentPane.add(textFieldDia);
		textFieldDia.setColumns(10);

		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] { "Enero", "Febrero", "Marzo", "Abril",
				"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		comboBoxMes.setBounds(110, 22, 100, 22);
		contentPane.add(comboBoxMes);

		comboBoxAno = new JComboBox<Integer>();
		comboBoxAno.setBounds(211, 22, 100, 22);
		for (int i = 1950; i <= 2025; i++) {
			comboBoxAno.addItem(i);
		}
		contentPane.add(comboBoxAno);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(10, 67, 46, 14);
		contentPane.add(lblHora);

		comboBoxHora = new JComboBox<Integer>();
		comboBoxHora.setBounds(62, 63, 58, 22);
		for (int i = 0; i < 24; i++) {
			comboBoxHora.addItem(i);
		}
		contentPane.add(comboBoxHora);

		comboBoxMinuto = new JComboBox<Integer>();
		comboBoxMinuto.setBounds(151, 63, 59, 22);
		for (int i = 0; i < 60; i++) {
			comboBoxMinuto.addItem(i);
		}
		contentPane.add(comboBoxMinuto);

		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(10, 103, 46, 14);
		contentPane.add(lblDe);

		textFieldDe = new JTextField();
		textFieldDe.setColumns(10);
		textFieldDe.setBounds(62, 100, 249, 20);
		contentPane.add(textFieldDe);

		JLabel lblHora2 = new JLabel(":");
		lblHora2.setBounds(130, 67, 11, 14);
		contentPane.add(lblHora2);

		JLabel lblPara = new JLabel("Para");
		lblPara.setBounds(10, 134, 46, 14);
		contentPane.add(lblPara);

		textFieldPara = new JTextField();
		textFieldPara.setColumns(10);
		textFieldPara.setBounds(62, 131, 249, 20);
		contentPane.add(textFieldPara);

		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setBounds(10, 162, 46, 14);
		contentPane.add(lblAsunto);

		textFieldAsunto = new JTextField();
		textFieldAsunto.setColumns(10);
		textFieldAsunto.setBounds(62, 159, 249, 20);
		contentPane.add(textFieldAsunto);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(10, 197, 67, 14);
		contentPane.add(lblContenido);

		textFieldContenido = new JTextField();
		textFieldContenido.setColumns(10);
		textFieldContenido.setBounds(72, 194, 239, 71);
		contentPane.add(textFieldContenido);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		btnOk.setBounds(381, 269, 58, 23);
		contentPane.add(btnOk);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				menu.setVisible(true);
			}
		});
		btnCancelar.setBounds(443, 269, 89, 23);
		contentPane.add(btnCancelar);
	}

	public void validar() {
		ArrayList<String> errores = new ArrayList<String>();
		int dia = -1;
		try {
			dia = Integer.parseInt(textFieldDia.getText());
		} catch (NumberFormatException error) {
			errores.add("Día debe ser un número entero");
		}
		String mes = (String) comboBoxMes.getSelectedItem();
		int ano = (int) comboBoxAno.getSelectedItem();
		int hora = (int) comboBoxHora.getSelectedItem();
		int minuto = (int) comboBoxMinuto.getSelectedItem();
		String de = textFieldDe.getText();
		String para = textFieldPara.getText();
		String asunto = textFieldAsunto.getText();
		String contenido = textFieldContenido.getText();

		if (dia != -1 && !(dia >= 1 && dia <= 31)) {
			errores.add("Día debe ser estar entre 1 y 31.");
		}
		if (de.trim().equals("")) {
			errores.add("El campo De es obligatorio.");
		}
		if (para.trim().equals("")) {
			errores.add("El campo Para es obligatorio.");
		}
		if (asunto.trim().equals("")) {
			errores.add("El campo Asunto es obligatorio.");
		}
		if (contenido.trim().equals("")) {
			errores.add("El campo Contenido es obligatorio.");
		}
		if (errores.size() != 0) {
			JOptionPane.showMessageDialog(null, "- " + String.join("\n- ", errores), "Error al añadir mensaje",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				controlador.añadirMensaje(dia, mes, ano, hora, minuto, de, para, asunto, contenido);
				JOptionPane.showMessageDialog(null, "Mensaje añadido correctamente.", "Añadir Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
				menu.setVisible(true);
			} catch (ParseException e) {
				System.out.println("Error al añadir la fecha al mensaje.");
			}
		}
	}
}
