package Modelo;

public class Vehiculo {
	protected int ID;
	protected String marca;
	protected String modelo;
	protected String estado;
	protected double precioPresupuesto; 
	protected double precioFinal; 
	protected boolean pagado;

	public Vehiculo(int iD, String marca, String modelo) {
		super();
		ID = iD;
		this.marca = marca;
		this.modelo = modelo;
		this.estado = "entrada"; 
		this.precioPresupuesto = 0.0;
		this.precioFinal = 0.0;
		this.pagado = false;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getPrecioPresupuesto() {
		return precioPresupuesto;
	}

	public void setPrecioPresupuesto(double precioPresupuesto) {
		this.precioPresupuesto = precioPresupuesto;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	@Override
	public String toString() {
		return "Tipo: " + getClass().getSimpleName() + '\n' + "Id: " + ID + '\n' + "Marca: " + marca + '\n'
				+ "Modelo: " + modelo + '\n' + "Estado: " + estado + '\n' + "PrecioPresupuesto: "
				+ precioPresupuesto + '\n' + "PrecioFinal: " + precioFinal + '\n' + "Pagado: " + pagado + '\n';
	}

}
