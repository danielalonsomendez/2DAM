package ejercicio7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HiloCaballo caballo1;
	private HiloCaballo caballo2;
	private HiloCaballo caballo3;
	private HiloCaballo caballo4;
	private JLabel lblGanador;
	private JButton btnEmpezar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBarCaballo3 = new JProgressBar();
		progressBarCaballo3.setStringPainted(true);
		progressBarCaballo3.setForeground(Color.MAGENTA);
		progressBarCaballo3.setBounds(118, 155, 326, 35);
		contentPane.add(progressBarCaballo3);

		JLabel lblCaballo1 = new JLabel("Caballo 1:");
		lblCaballo1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaballo1.setForeground(Color.PINK);
		lblCaballo1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCaballo1.setBounds(13, 63, 95, 35);
		contentPane.add(lblCaballo1);

		JProgressBar progressBarCaballo1 = new JProgressBar();
		progressBarCaballo1.setStringPainted(true);
		progressBarCaballo1.setForeground(Color.PINK);
		progressBarCaballo1.setBounds(118, 63, 326, 35);
		contentPane.add(progressBarCaballo1);

		JLabel lblCaballo2 = new JLabel("Caballo 2:");
		lblCaballo2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaballo2.setForeground(Color.BLUE);
		lblCaballo2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCaballo2.setBounds(13, 109, 95, 35);
		contentPane.add(lblCaballo2);

		JProgressBar progressBarCaballo2 = new JProgressBar();
		progressBarCaballo2.setStringPainted(true);
		progressBarCaballo2.setForeground(Color.LIGHT_GRAY);
		progressBarCaballo2.setBounds(118, 109, 326, 35);
		contentPane.add(progressBarCaballo2);

		JLabel lblCaballo3 = new JLabel("Caballo 3:");
		lblCaballo3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaballo3.setForeground(Color.MAGENTA);
		lblCaballo3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCaballo3.setBounds(13, 155, 95, 35);
		contentPane.add(lblCaballo3);

		JLabel lblCaballo4 = new JLabel("Caballo 4:");
		lblCaballo4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaballo4.setForeground(Color.GREEN);
		lblCaballo4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCaballo4.setBounds(13, 201, 95, 35);
		contentPane.add(lblCaballo4);

		JProgressBar progressBarCaballo4 = new JProgressBar();
		progressBarCaballo4.setStringPainted(true);
		progressBarCaballo4.setForeground(Color.GREEN);
		progressBarCaballo4.setBounds(118, 201, 326, 35);
		contentPane.add(progressBarCaballo4);

		btnEmpezar = new JButton("Empieza la carrera");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HiloCaballo.carreraFinalizada = false;
				btnEmpezar.setEnabled(false);
				lblGanador.setText("Ganador:");
				caballo1 = new HiloCaballo("Caballo 1", progressBarCaballo1, Trampas.p1);
				caballo2 = new HiloCaballo("Caballo 2", progressBarCaballo2, Trampas.p2);
				caballo3 = new HiloCaballo("Caballo 3", progressBarCaballo3, Trampas.p3);
				caballo4 = new HiloCaballo("Caballo 4", progressBarCaballo4, Trampas.p4);
				caballo1.start();
				caballo2.start();
				caballo3.start();
				caballo4.start();
			}
		});
		btnEmpezar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEmpezar.setForeground(Color.BLACK);
		btnEmpezar.setBounds(195, 259, 168, 23);
		contentPane.add(btnEmpezar);

		JButton btnTrampas = new JButton("Trampas");
		btnTrampas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Trampas frame = new Trampas();
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnTrampas.setBounds(418, 300, 90, 23);
		contentPane.add(btnTrampas);

		lblGanador = new JLabel("Ganador:");
		lblGanador.setHorizontalAlignment(SwingConstants.LEFT);
		lblGanador.setForeground(Color.BLACK);
		lblGanador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGanador.setBounds(118, 11, 326, 35);
		contentPane.add(lblGanador);

		HiloCaballo.btnEmpezar = btnEmpezar;
		HiloCaballo.ganador = lblGanador;

	}
}