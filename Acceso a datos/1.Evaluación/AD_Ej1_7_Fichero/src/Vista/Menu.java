package Vista;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;
	Menu frameMenu = this; // 'this' es el frame actual

	/**
	 * Create the frame.
	 */
	public Menu(Controlador Controlador) {
		controlador = Controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCargar = new JButton("Cargar mensajes");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.leerArchivo();
					JOptionPane.showMessageDialog(null,
							"Se han cargado en memoria " + controlador.todoslosMensajes().size() + " mensajes.",
							"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException e1) {
					System.out.println("Archivo no encontrado");
				} catch (IOException e1) {
					System.out.println("Error IO");
				} catch (ParseException e1) {
					System.out.println("Error Parse");
				}

			}
		});
		btnCargar.setBounds(32, 28, 136, 23);
		contentPane.add(btnCargar);

		JButton btnAñadir = new JButton("Añadir mensajes");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnadirMensaje frame = new AnadirMensaje(frameMenu, controlador);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAñadir.setBounds(32, 165, 136, 23);
		contentPane.add(btnAñadir);

		JButton btnGuardar = new JButton("Guardar mensajes");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.guardarArchivo();
					JOptionPane.showMessageDialog(null,
							"Los mensajes en memoria, han sido guardados en el fichero MENSAJES.TXT", "Mensaje",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					System.out.println("Error IO");
				}
			}
		});
		btnGuardar.setBounds(243, 50, 154, 23);
		contentPane.add(btnGuardar);

		JButton btnImprimir = new JButton("Imprimir mensajes");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImprimirMensajes frame = new ImprimirMensajes(frameMenu, controlador);
				frame.setVisible(true);
				dispose();
			}
		});
		btnImprimir.setBounds(243, 91, 154, 23);
		contentPane.add(btnImprimir);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(316, 211, 89, 23);
		contentPane.add(btnSalir);

	}

}
