package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Socket.Servidor;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaServidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField txtConexionesActuales;
	private static Servidor servidor;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServidor frame = new VentanaServidor();
					frame.setVisible(true);
					servidor = new Servidor(frame.textArea, frame.txtConexionesActuales);
					servidor.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 403);
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
				servidor.detener();
			}
		});
		btnSalir.setBounds(232, 318, 89, 23);
		contentPane.add(btnSalir);

		txtConexionesActuales = new JTextField();
		txtConexionesActuales.setText("Conexiones actuales = 0");
		txtConexionesActuales.setEditable(false);
		txtConexionesActuales.setEnabled(false);
		txtConexionesActuales.setBounds(10, 22, 542, 20);
		contentPane.add(txtConexionesActuales);
		txtConexionesActuales.setColumns(10);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 53, 542, 254);
		contentPane.add(textArea);

	}
}
