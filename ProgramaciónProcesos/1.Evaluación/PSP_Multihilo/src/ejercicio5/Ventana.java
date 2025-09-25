package ejercicio5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HiloContador hilo1;
	private HiloContador hilo2;
	private HiloContador hilo3;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 264, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHilo1BajarPrioridad = new JButton("--");
		btnHilo1BajarPrioridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo1.bajarPrioridad();
			}
		});
		btnHilo1BajarPrioridad.setBounds(10, 11, 56, 23);
		contentPane.add(btnHilo1BajarPrioridad);
		
		JButton btnFinHilo1 = new JButton("Fin hilo 1");
		btnFinHilo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo1.finalizar();
			}
		});
		btnFinHilo1.setBounds(76, 11, 95, 23);
		contentPane.add(btnFinHilo1);
		
		JButton btnHilo1AumentarPrioridad = new JButton("++");
		btnHilo1AumentarPrioridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo1.subirPrioridad();
			}
		});
		btnHilo1AumentarPrioridad.setBounds(181, 11, 56, 23);
		contentPane.add(btnHilo1AumentarPrioridad);
		
		JButton btnHilo2BajarPrioridad = new JButton("--");
		btnHilo2BajarPrioridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo2.bajarPrioridad();
			}
		});
		btnHilo2BajarPrioridad.setBounds(10, 45, 56, 23);
		contentPane.add(btnHilo2BajarPrioridad);
		
		JButton btnFinHilo2 = new JButton("Fin hilo 2");
		btnFinHilo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo2.finalizar();
			}
		});
		btnFinHilo2.setBounds(76, 45, 95, 23);
		contentPane.add(btnFinHilo2);
		
		JButton btnHilo2AumentarPrioridad = new JButton("++");
		btnHilo2AumentarPrioridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo2.subirPrioridad();
			}
		});
		btnHilo2AumentarPrioridad.setBounds(181, 45, 56, 23);
		contentPane.add(btnHilo2AumentarPrioridad);
		
		JButton btnHilo3BajarPrioridad = new JButton("--");
		btnHilo3BajarPrioridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo3.bajarPrioridad();
			}
		});
		btnHilo3BajarPrioridad.setBounds(10, 79, 56, 23);
		contentPane.add(btnHilo3BajarPrioridad);
		
		JButton btnFinHilo3 = new JButton("Fin hilo 3");
		btnFinHilo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo3.finalizar();
			}
		});
		btnFinHilo3.setBounds(76, 79, 95, 23);
		contentPane.add(btnFinHilo3);
		
		JButton btnHilo3AumentarPrioridad = new JButton("++");
		btnHilo3AumentarPrioridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo3.subirPrioridad();
			}
		});
		btnHilo3AumentarPrioridad.setBounds(181, 79, 56, 23);
		contentPane.add(btnHilo3AumentarPrioridad);
		
		JLabel lblContadorHilo1 = new JLabel("Hilo 1: 0");
		lblContadorHilo1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContadorHilo1.setBounds(10, 154, 95, 23);
		contentPane.add(lblContadorHilo1);
		
		JLabel lblPriHilo1 = new JLabel("Pri: 0");
		lblPriHilo1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPriHilo1.setBounds(157, 154, 80, 23);
		contentPane.add(lblPriHilo1);
		
		JLabel lblContadorHilo2 = new JLabel("Hilo 2: 0");
		lblContadorHilo2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContadorHilo2.setBounds(10, 188, 95, 23);
		contentPane.add(lblContadorHilo2);
		
		JLabel lblPriHilo2 = new JLabel("Pri: 0");
		lblPriHilo2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPriHilo2.setBounds(157, 188, 80, 23);
		contentPane.add(lblPriHilo2);
		
		JLabel lblContadorHilo3 = new JLabel("Hilo 3: 0");
		lblContadorHilo3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContadorHilo3.setBounds(10, 222, 95, 23);
		contentPane.add(lblContadorHilo3);
		
		JLabel lblPriHilo3 = new JLabel("Pri: 0");
		lblPriHilo3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPriHilo3.setBounds(157, 222, 80, 23);
		contentPane.add(lblPriHilo3);
		
		JButton btnFinHilo123 = new JButton("Finalizar Todos");
		btnFinHilo123.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilo1.finalizar();
				hilo2.finalizar();
				hilo3.finalizar();
			}
		});
		btnFinHilo123.setBounds(10, 111, 227, 23);
		contentPane.add(btnFinHilo123);
		
		hilo1 = new HiloContador(lblContadorHilo1, lblPriHilo1,"Hilo 1");
		hilo2 = new HiloContador(lblContadorHilo2, lblPriHilo2,"Hilo 2");
		hilo3 = new HiloContador(lblContadorHilo3, lblPriHilo3,"Hilo 3");
		hilo1.start();
		hilo2.start();
		hilo3.start();

	}
}
