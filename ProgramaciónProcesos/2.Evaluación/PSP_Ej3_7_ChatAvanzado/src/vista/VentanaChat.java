package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Socket.Cliente;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class VentanaChat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField txtMensaje;
	private JButton btnEnviar;
	private Cliente cliente;


	/**
	 * Create the frame.
	 */
	public VentanaChat(String nick,Socket socket) {
		setTitle("Chat - " + nick);
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
			    cliente.enviarMensaje("*", nick); 
			}
		});
		btnSalir.setBounds(232, 318, 89, 23);
		contentPane.add(btnSalir);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 53, 542, 254);
		contentPane.add(textArea);
		
	
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(10, 22, 443, 20);
		contentPane.add(txtMensaje);
		txtMensaje.setColumns(10);
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(463, 19, 89, 23);
		cliente = new Cliente(socket, textArea, btnEnviar,nick,this);
		cliente.run();
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String texto = txtMensaje.getText().trim();
				    if (!texto.isEmpty()) {
				        cliente.enviarMensaje(texto, nick);
				        txtMensaje.setText("");
				    }
			}
		});
		txtMensaje.addActionListener(e -> {
		    btnEnviar.doClick();
		});
		contentPane.add(btnEnviar);

	}
	
}
