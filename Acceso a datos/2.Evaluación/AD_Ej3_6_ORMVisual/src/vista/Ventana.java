package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import javax.swing.JButton;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelProvincia panelProvincia;
	private PanelMunicipio panelMunicipio;
	private PanelEstacionesEspacios panelEstacionesEspacios;
	private PanelEstaciones panelEstaciones;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					new Controlador(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	


	private void mCrearPanelProvincia() {
		panelProvincia = new PanelProvincia();
		contentPane.add(panelProvincia);
		panelProvincia.setBounds(10, 36, 768, 380);
		panelProvincia.setVisible(false);
	}
	private void mCrearPanelMunicipio() {
		panelMunicipio = new PanelMunicipio();
		contentPane.add(panelMunicipio);
		panelMunicipio.setBounds(10, 36, 768, 380);
		panelMunicipio.setVisible(false);
	}
	private void mCrearPanelEstacionesEspacio() {
		panelEstacionesEspacios = new PanelEstacionesEspacios();
		contentPane.add(panelEstacionesEspacios);
		panelEstacionesEspacios.setBounds(10, 36, 768, 380);
		panelEstacionesEspacios.setVisible(false);
	}
	
	private void mCrearPanelEstaciones() {
		panelEstaciones = new PanelEstaciones();
		contentPane.add(panelEstaciones);
		panelEstaciones.setBounds(10, 36, 928, 380);
		panelEstaciones.setVisible(false);
	}
	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 2, 124, 23);
		contentPane.add(btnVolver);
		btnVolver.setVisible(false);
		mCrearPanelProvincia();
		mCrearPanelMunicipio();
		mCrearPanelEstacionesEspacio();
		mCrearPanelEstaciones();
	}

	public PanelProvincia getPanelProvincia() {
		return panelProvincia;
	}

	public void setPanelProvincia(PanelProvincia panelProvincia) {
		this.panelProvincia = panelProvincia;
	}

	public PanelMunicipio getPanelMunicipio() {
		return panelMunicipio;
	}

	public void setPanelMunicipio(PanelMunicipio panelMunicipio) {
		this.panelMunicipio = panelMunicipio;
	}

	public PanelEstacionesEspacios getPanelEstacionesEspacios() {
		return panelEstacionesEspacios;
	}

	public void setPanelEstacionesEspacios(PanelEstacionesEspacios panelEstacionesEspacios) {
		this.panelEstacionesEspacios = panelEstacionesEspacios;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public PanelEstaciones getPanelEstaciones() {
		return panelEstaciones;
	}

	public void setPanelEstaciones(PanelEstaciones panelEstaciones) {
		this.panelEstaciones = panelEstaciones;
	}
	


}
