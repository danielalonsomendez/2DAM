package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.CentrosMeteorologicos;
import modelo.EspaciosNaturales;
import modelo.HibernateUtil;
import modelo.MedicionesCentroMet;
import modelo.MedicionesCentroMetId;
import modelo.Municipios;
import modelo.MunicipiosEspaciosNat;
import modelo.MunicipiosEspaciosNatId;
import modelo.Provincias;
import vista.Ventana;

public class Controlador implements ActionListener {
	private Ventana vista;
	private Session session;

	public Controlador(Ventana vista) {
		this.vista = vista;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		session = sesion.openSession();
		vista.getPanelProvincia().setVisible(true);
		mRellenarProvincias();

		vista.getPanelProvincia().getBtnIncluir().setActionCommand("INCLUIR");
		vista.getPanelProvincia().getBtnIncluir().addActionListener(this);

		vista.getPanelProvincia().getBtnSeleccionar().setActionCommand("SELECCIONAR_PROVINCIA");
		vista.getPanelProvincia().getBtnSeleccionar().addActionListener(this);

		vista.getPanelMunicipio().getBtnSeleccionar().setActionCommand("SELECCIONAR_MUNICIPIO");
		vista.getPanelMunicipio().getBtnSeleccionar().addActionListener(this);

		vista.getPanelEstacionesEspacios().getBtnSeleccionar().setActionCommand("SELECCIONAR_ESTACION");
		vista.getPanelEstacionesEspacios().getBtnSeleccionar().addActionListener(this);

		vista.getBtnVolver().setActionCommand("VOLVER");
		vista.getBtnVolver().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String cmd = e.getActionCommand();
		switch (cmd) {
		case "VOLVER":
			mVolver();
			break;
		case "INCLUIR":
			mGuardarProvincias();
			mGuardarMunicipios();
			mGuardarEspaciosNaturales();
			mGuardarCentroMeterologico();
			mGuardarMedicionesCentroMeteorologico();
			
			mIncluir();
			mRellenarProvincias();
			break;
		case "SELECCIONAR_PROVINCIA":
			mSeleccionarProvincia();
			break;
		case "SELECCIONAR_MUNICIPIO":
			mSeleccionarMunicipio();
			break;
		case "SELECCIONAR_ESTACION":
			mSeleccionarEstacion();
			break;
		default:
			break;
		}

	}
	public void mGuardarProvincias() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		ArrayList<Provincias> provincias = Provincias.obtenerListaProvincias(session);		
		File file = new File("provincias.json");
		try (FileWriter writer = new FileWriter(file)) {
			 gson.toJson(provincias,writer);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void mGuardarMunicipios() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
		ArrayList<Municipios> municipios = Municipios.obtenerListaMunicipios(session);
		File file = new File("municipios.json");
		try (FileWriter writer = new FileWriter(file)) {
			 gson.toJson(municipios,writer);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void mGuardarEspaciosNaturales() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
		ArrayList<EspaciosNaturales> espacios = EspaciosNaturales.obtenerListaEspacios(session);
		File file = new File("espaciosnaturales.json");
		try (FileWriter writer = new FileWriter(file)) {
			 gson.toJson(espacios,writer);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void mGuardarCentroMeterologico() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
		ArrayList<CentrosMeteorologicos> centros = CentrosMeteorologicos.obtenerListaCentros(session);
		File file = new File("centrosmeterologicos.json");
		try (FileWriter writer = new FileWriter(file)) {
			 gson.toJson(centros,writer);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void mGuardarMedicionesCentroMeteorologico() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
		ArrayList<MedicionesCentroMet> mediciones = MedicionesCentroMet.obtenerListaMediciones(session);
		File file = new File("mediciones.json");
		try (FileWriter writer = new FileWriter(file)) {
			 gson.toJson(mediciones,writer);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	

	public void mVolver() {
		if (vista.getPanelEstaciones().isVisible()) {
			vista.getPanelEstaciones().setVisible(false);
			vista.getPanelEstacionesEspacios().setVisible(true);
		} else if (vista.getPanelEstacionesEspacios().isVisible()) {
			vista.getPanelEstacionesEspacios().setVisible(false);
			vista.getPanelMunicipio().setVisible(true);
		} else if (vista.getPanelMunicipio().isVisible()) {
			vista.getPanelMunicipio().setVisible(false);
			vista.getPanelProvincia().setVisible(true);
			vista.getBtnVolver().setVisible(false);
		} else {
			vista.getBtnVolver().setVisible(false);
		}
	}

	public void mRellenarProvincias() {
		vista.getPanelProvincia().getComboBox().removeAllItems();
		ArrayList<Provincias> provincias = Provincias.obtenerListaProvincias(session);
		for (Provincias provincia : provincias) {
			vista.getPanelProvincia().getComboBox().addItem(provincia.getNombre());
		}
	}

	public void mIncluir() {
		Transaction tx = session.beginTransaction();

		Provincias provincia = new Provincias();
		provincia.setNombre("Nueva Provincia");

		session.persist(provincia);

		Municipios municipio = new Municipios();
		municipio.setNombre("Nuevo Municipio");
		municipio.setDescripcion("Descripción del nuevo municipio");
		municipio.setCodMunicipio(9999);
		municipio.setProvincias(provincia);

		session.persist(municipio);

		EspaciosNaturales espacio = new EspaciosNaturales();
		espacio.setNombre("Nuevo Espacio Natural");
		espacio.setDescripcion("Descripción del nuevo espacio natural");
		espacio.setTipo("Parque");
		espacio.setCategoria("Nacional");
		espacio.setLatitud(40.0);
		espacio.setLongitud(-3.0);

		session.persist(espacio);

		CentrosMeteorologicos centro = new CentrosMeteorologicos();
		centro.setNombre("Nuevo Centro Meteorológico");
		centro.setDireccion("Calle Falsa 123");
		centro.setLatitud(40.1);
		centro.setLongitud(-3.1);
		centro.setUrl("http://a.com");
		centro.setHash("abc123");
		centro.setMunicipios(municipio);

		session.persist(centro);
		session.flush();

		MedicionesCentroMetId medicionId = new MedicionesCentroMetId();
		medicionId.setIdCentroMet(centro.getIdCentroMet());
		medicionId.setFecha(new Date(System.currentTimeMillis()));
		medicionId.setHora(new Time(System.currentTimeMillis()));

		MedicionesCentroMet medicion = new MedicionesCentroMet();
		medicion.setId(medicionId);
		medicion.setCentrosMeteorologicos(centro);
		medicion.setTempAmbiente(22.0f);
		medicion.setHRelativa(50);
		medicion.setVViento(10.0f);
		medicion.setDirViento(180);
		medicion.setPAtmosferica(1013.25f);
		medicion.setPrecip(0.0f);
		medicion.setRadSolar(500.0f);
		medicion.setIca("Bueno");

		session.persist(medicion);

		MunicipiosEspaciosNat men = new MunicipiosEspaciosNat();
		men.setId(new MunicipiosEspaciosNatId(espacio.getIdEspacio(), municipio.getIdMunicipio()));
		men.setMunicipios(municipio);
		men.setEspaciosNaturales(espacio);

		session.persist(men);

		tx.commit();
	}

	public void mSeleccionarProvincia() {
		Provincias provincia = mConseguirProvinciaSeleccionada();
		if (provincia == null) {
			return;
		}
		vista.getPanelMunicipio().setVisible(true);
		vista.getPanelProvincia().setVisible(false);
		vista.getBtnVolver().setVisible(true);
		mRellenarMunicipios(provincia);
	}

	public void mSeleccionarMunicipio() {
		Municipios municipio = mConseguirMunicipioSeleccionado();
		if (municipio == null) {
			return;
		}
		vista.getPanelMunicipio().setVisible(false);
		vista.getPanelProvincia().setVisible(false);
		vista.getPanelEstacionesEspacios().setVisible(true);
		vista.getBtnVolver().setVisible(true);
		mRellenarEstacionesEspacios(municipio);
	}

	private void mSeleccionarEstacion() {
		CentrosMeteorologicos estacion = mConseguirEstacionSeleccionada();
		if (estacion == null) {
			return;
		}
		vista.getPanelMunicipio().setVisible(false);
		vista.getPanelProvincia().setVisible(false);
		vista.getPanelEstacionesEspacios().setVisible(false);
		vista.getPanelEstaciones().setVisible(true);
		vista.getBtnVolver().setVisible(true);
		mRellenarMedicionesEstacion(estacion);
	}

	private CentrosMeteorologicos mConseguirEstacionSeleccionada() {
		String estacionSeleccionada = (String) vista.getPanelEstacionesEspacios().getTable()
				.getValueAt(vista.getPanelEstacionesEspacios().getTable().getSelectedRow(), 0);
		CentrosMeteorologicos estacion = new CentrosMeteorologicos();
		try {
			estacion.obtenerCentroPorNombreYMunicipio(session, estacionSeleccionada, mConseguirMunicipioSeleccionado());
		} catch (Exception e) {
			return null;
		}
		return estacion;
	}

	public Provincias mConseguirProvinciaSeleccionada() {
		String provinciaSeleccionada = (String) vista.getPanelProvincia().getComboBox().getSelectedItem();
		Provincias provincia = new Provincias();
		try {
			provincia.obtenerProvinciaPorNombre(session, provinciaSeleccionada);
		} catch (Exception e) {
			return null;
		}

		return provincia;
	}

	public Municipios mConseguirMunicipioSeleccionado() {
		Provincias provincia = mConseguirProvinciaSeleccionada();

		String municipioSeleccionado = (String) vista.getPanelMunicipio().getTable()
				.getValueAt(vista.getPanelMunicipio().getTable().getSelectedRow(), 0);
		Municipios municipio = new Municipios();
		try {
			municipio.obtenerMunicipioPorNombreYProvincia(session, municipioSeleccionado, provincia.getIdProvincia());
		} catch (Exception e) {
			return null;
		}

		return municipio;
	}

	public void mRellenarMunicipios(Provincias provincia) {
		vista.getPanelMunicipio().getModelo().setRowCount(0);
		ArrayList<Municipios> municipios = Municipios.obtenerListaMunicipiosProvincia(session,
				provincia.getIdProvincia());
		for (Municipios municipio : municipios) {
			vista.getPanelMunicipio().getModelo().addRow(new Object[] { municipio.getNombre() });
		}
	}

	public void mRellenarEstacionesEspacios(Municipios municipio) {

		vista.getPanelEstacionesEspacios().getModelo().setRowCount(0);
		vista.getPanelEstacionesEspacios().getModelo2().setRowCount(0);
		ArrayList<CentrosMeteorologicos> centros = CentrosMeteorologicos.obtenerListaCentrosMunicipio(session,
				municipio);
		for (CentrosMeteorologicos centro : centros) {
			vista.getPanelEstacionesEspacios().getModelo().addRow(new Object[] { centro.getNombre() });
		}

		ArrayList<EspaciosNaturales> espacios = EspaciosNaturales.obtenerListaEspaciosMunicipio(session,
				municipio.getIdMunicipio());
		for (EspaciosNaturales espacio : espacios) {
			vista.getPanelEstacionesEspacios().getModelo2().addRow(new Object[] { espacio.getNombre() });
			System.out.println(espacio.getNombre());
		}
	}

	public void mRellenarMedicionesEstacion(CentrosMeteorologicos estacion) {
		vista.getPanelEstaciones().getModelo().setRowCount(0);
		ArrayList<MedicionesCentroMet> mediciones = MedicionesCentroMet.obtenerListaMedicionesCentroMet(session,
				estacion.getIdCentroMet());
		vista.getPanelEstaciones().getLblTitulo().setText( estacion.getNombre());
		for (MedicionesCentroMet medicion : mediciones) {
			vista.getPanelEstaciones().getModelo()
					.addRow(new Object[] { medicion.getId().getFecha(), medicion.getId().getHora(),
							medicion.getTempAmbiente(), medicion.getHRelativa(), medicion.getVViento(),
							medicion.getDirViento(), medicion.getPAtmosferica(), medicion.getPrecip(),
							medicion.getRadSolar(), medicion.getIca() });
		}
	}
}
