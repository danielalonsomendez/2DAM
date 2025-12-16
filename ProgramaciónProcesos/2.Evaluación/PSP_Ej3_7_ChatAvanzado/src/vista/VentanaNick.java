package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class VentanaNick extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblMensajeError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNick frame = new VentanaNick();
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
	public VentanaNick() {
		setTitle("Ventana cliente entrada");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 189);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(10, 82, 89, 23);
		contentPane.add(btnSalir);

		lblMensajeError = new JLabel("");
		lblMensajeError.setForeground(new Color(255, 0, 0));
		lblMensajeError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMensajeError.setBounds(10, 116, 187, 14);
		contentPane.add(lblMensajeError);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().trim().isEmpty()) {
					try {
						Socket cliente = new Socket("localhost", 5000);

						VentanaChat ventanaChat = new VentanaChat(textField.getText().trim(), cliente);
						ventanaChat.setVisible(true);

						dispose();
					} catch (Exception ex) {
						ex.printStackTrace();
						lblMensajeError.setText("Error al conectarse");
					}
				} else {
					lblMensajeError.setText("El nick no puede estar vac\u00EDo");
				}
			}
		});
		btnAceptar.setBounds(207, 82, 89, 23);
		contentPane.add(btnAceptar);

		JLabel lblNewLabel = new JLabel("Introduce tu nick:");
		lblNewLabel.setBounds(13, 24, 187, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(12, 45, 282, 20);
		contentPane.add(textField);
		textField.setColumns(10);

	}
	public void setError(String message) {
		lblMensajeError.setText(message);
	}
	
}
