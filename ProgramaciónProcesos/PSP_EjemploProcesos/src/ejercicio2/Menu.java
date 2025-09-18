package ejercicio2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSistema;
	private JTextField txtCMD;
	private JTextField txt5Lectura;
	private JLabel lblSistemaPID;
	private JLabel lblSistemaPIDPadre;
	private JLabel lblCMDPIDPadre;
	private JLabel lblCMDPID;
	private JLabel lbl5LecturaPID;
	private JLabel lbl5LecturaPIDPadre;
	private JTextPane textPaneCMD;
	private JTextPane textPane5Lectura;

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
		setBounds(100, 100, 566, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtSistema = new JTextField();
		txtSistema.setBounds(91, 41, 86, 20);
		contentPane.add(txtSistema);
		txtSistema.setColumns(10);

		JButton btnSistema = new JButton("Start");
		btnSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programaSistema();
			}
		});
		btnSistema.setBounds(88, 72, 89, 23);
		contentPane.add(btnSistema);

		JLabel lblPID = new JLabel("PID");
		lblPID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPID.setToolTipText("PID");
		lblPID.setBounds(10, 109, 46, 14);
		contentPane.add(lblPID);

		JLabel lblPidPadre = new JLabel("PID Padre");
		lblPidPadre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPidPadre.setToolTipText("PID");
		lblPidPadre.setBounds(10, 140, 64, 14);
		contentPane.add(lblPidPadre);

		JButton btnCMD = new JButton("Start");
		btnCMD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programaCMD();
			}
		});
		btnCMD.setBounds(199, 72, 89, 23);
		contentPane.add(btnCMD);

		txtCMD = new JTextField();
		txtCMD.setColumns(10);
		txtCMD.setBounds(202, 41, 86, 20);
		contentPane.add(txtCMD);

		JButton btn5Lectura = new JButton("Start");
		btn5Lectura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programa5Lectura();
			}
		});
		btn5Lectura.setBounds(310, 72, 89, 23);
		contentPane.add(btn5Lectura);

		txt5Lectura = new JTextField();
		txt5Lectura.setColumns(10);
		txt5Lectura.setBounds(313, 41, 86, 20);
		contentPane.add(txt5Lectura);

		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setToolTipText("PID");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 176, 64, 14);
		contentPane.add(lblResultado);

		textPaneCMD = new JTextPane();
		textPaneCMD.setBounds(88, 201, 212, 203);
		contentPane.add(textPaneCMD);

		textPane5Lectura = new JTextPane();
		textPane5Lectura.setBounds(310, 201, 212, 203);
		contentPane.add(textPane5Lectura);

		lblSistemaPID = new JLabel("");
		lblSistemaPID.setBounds(110, 109, 46, 14);
		contentPane.add(lblSistemaPID);

		lblSistemaPIDPadre = new JLabel("");
		lblSistemaPIDPadre.setBounds(110, 140, 46, 14);
		contentPane.add(lblSistemaPIDPadre);

		lblCMDPIDPadre = new JLabel("");
		lblCMDPIDPadre.setBounds(218, 140, 46, 14);
		contentPane.add(lblCMDPIDPadre);

		lblCMDPID = new JLabel("");
		lblCMDPID.setBounds(218, 109, 46, 14);
		contentPane.add(lblCMDPID);

		lbl5LecturaPID = new JLabel("");
		lbl5LecturaPID.setBounds(310, 109, 223, 14);
		contentPane.add(lbl5LecturaPID);

		lbl5LecturaPIDPadre = new JLabel("");
		lbl5LecturaPIDPadre.setBounds(333, 140, 46, 14);
		contentPane.add(lbl5LecturaPIDPadre);

	}

	public Process ejecutarProceso(ProcessBuilder pb, JLabel pid, JLabel pidpadre) throws IOException {
		Process proces = pb.start();
		pid.setText(pid.getText() + proces.pid() + " ");
		pidpadre.setText(proces.toHandle().parent().get().pid() + "");
		return proces;
	}

	public String respuestaProceso(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String linea = null;
		StringBuilder resultado = new StringBuilder();
		while ((linea = reader.readLine()) != null) {
			resultado.append(linea).append("\n");
		}
		return resultado.toString();
	}

	public void programaSistema() {
		try {
			lblSistemaPID.setText("");
			ProcessBuilder pb = new ProcessBuilder(txtSistema.getText());
			ejecutarProceso(pb, lblSistemaPID, lblSistemaPIDPadre);
		} catch (IOException e1) {
			lblSistemaPID.setText("Error");
			lblSistemaPIDPadre.setText("Error");
		}
	}

	public void programaCMD() {
		try {
			lblCMDPID.setText("");
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", txtCMD.getText());
			Process process = ejecutarProceso(pb, lblCMDPID, lblCMDPIDPadre);
			textPaneCMD.setText(respuestaProceso(process));
		} catch (IOException e1) {
			lblCMDPID.setText("Error");
			lblCMDPIDPadre.setText("Error");
			textPaneCMD.setText("Error:" + e1.getMessage());
		}
	}

	public void programa5Lectura() {
		String respuesta = "";
		lbl5LecturaPID.setText("");
		for (int i = 0; i < 5; i++) {
			try {
				ProcessBuilder pb = new ProcessBuilder("java", "EjemploLectura.EjemploLectura");
				pb.directory(new File("bin"));
				Process process = ejecutarProceso(pb, lbl5LecturaPID, lbl5LecturaPIDPadre);
				process.getOutputStream().write((txt5Lectura.getText() + "\n").getBytes());
				process.getOutputStream().flush();
				respuesta += respuestaProceso(process);
			} catch (IOException e1) {
				lbl5LecturaPID.setText("Error");
				lbl5LecturaPIDPadre.setText("Error");
				textPane5Lectura.setText("Error:" + e1.getMessage());
			}
		}
		textPane5Lectura.setText(respuesta);
	}
}
