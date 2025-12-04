package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			Socket cliente = new Socket("localhost", 5000);
			DataInputStream socketIS = new DataInputStream(cliente.getInputStream());

			File file = new File("recibido.png");
			FileOutputStream fileOS = new FileOutputStream(file);

			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = socketIS.read(buffer)) != -1) {
				fileOS.write(buffer, 0, bytesRead);
			}

			fileOS.close();
			socketIS.close();
			cliente.close();
			ImageIcon fotoE = new ImageIcon("recibido.png");
			JLabel lblFoto = new JLabel(fotoE);
			lblFoto.setBounds(48, 63, 320, 122);
			contentPane.add(lblFoto);
		} catch (Exception e) {
			ImageIcon fotoE = new ImageIcon("error.png");
			JLabel lblFoto = new JLabel(fotoE);
			lblFoto.setForeground(Color.red);
			lblFoto.setBounds(48, 63, 320, 122);

			contentPane.add(lblFoto);
		}

	}
}
