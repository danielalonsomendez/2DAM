package ejercicio7;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trampas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCaballo1;
	private JTextField textFieldCaballo2;
	private JTextField textFieldCaballo3;
	private JTextField textFieldCaballo4;
	public static int p1 = 6;
	public static int p2 = 6;
	public static int p3 = 6;
	public static int p4 = 6;

	/**
	 * Create the frame.
	 */
	public Trampas() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 208, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCaballo1 = new JLabel("Caballo1");
		lblCaballo1.setBounds(10, 11, 66, 20);
		contentPane.add(lblCaballo1);

		textFieldCaballo1 = new JTextField();
		textFieldCaballo1.setBounds(81, 11, 100, 20);
		contentPane.add(textFieldCaballo1);
		textFieldCaballo1.setColumns(10);

		JLabel lblCaballo2 = new JLabel("Caballo 2");
		lblCaballo2.setBounds(10, 42, 66, 20);
		contentPane.add(lblCaballo2);

		textFieldCaballo2 = new JTextField();
		textFieldCaballo2.setColumns(10);
		textFieldCaballo2.setBounds(81, 42, 100, 20);
		contentPane.add(textFieldCaballo2);

		JLabel lblCaballo3 = new JLabel("Caballo 3");
		lblCaballo3.setBounds(10, 73, 66, 20);
		contentPane.add(lblCaballo3);

		textFieldCaballo3 = new JTextField();
		textFieldCaballo3.setColumns(10);
		textFieldCaballo3.setBounds(81, 73, 100, 20);
		contentPane.add(textFieldCaballo3);

		JLabel lblCaballo4 = new JLabel("Caballo 4");
		lblCaballo4.setBounds(10, 101, 66, 20);
		contentPane.add(lblCaballo4);

		textFieldCaballo4 = new JTextField();
		textFieldCaballo4.setColumns(10);
		textFieldCaballo4.setBounds(81, 101, 100, 20);
		contentPane.add(textFieldCaballo4);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trampas.p1 = Integer.parseInt(textFieldCaballo1.getText());
				Trampas.p2 = Integer.parseInt(textFieldCaballo2.getText());
				Trampas.p3 = Integer.parseInt(textFieldCaballo3.getText());
				Trampas.p4 = Integer.parseInt(textFieldCaballo4.getText());
				dispose();
			}
		});
		btnGuardar.setBounds(10, 132, 171, 23);
		contentPane.add(btnGuardar);
		textFieldCaballo1.setText(p1 + "");
		textFieldCaballo2.setText(p2 + "");
		textFieldCaballo3.setText(p3 + "");
		textFieldCaballo4.setText(p4 + "");

	}
}