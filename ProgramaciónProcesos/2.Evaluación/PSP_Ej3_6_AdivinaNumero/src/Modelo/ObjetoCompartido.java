package Modelo;

public class ObjetoCompartido {
	private int numero;
	private boolean acabo;
	private int ganador;

	public ObjetoCompartido() {
	}

	public ObjetoCompartido(int numero, boolean acabo, int ganador) {
		super();
		this.numero = numero;
		this.acabo = acabo;
		this.ganador = ganador;
	}

	public ObjetoCompartido(int numeroAleatorio) {
		this.numero = numeroAleatorio;
		this.acabo = false;
		this.ganador = -1;
	}

	@Override
	public String toString() {
		return "ObjetoCompartido [numero=" + numero + ", acabo=" + acabo + ", ganador=" + ganador + "]";
	}

	public boolean seAcabo() {
		return acabo;
	}

	public int getGanador() {
		return ganador;
	}

	public synchronized String nuevaJugada(int jugador, int suNumero) {
		if (acabo) {
			return "LO SENTIMOS, EL JUEGO HA TERMINADO, HAN ADIVINADO EL Nº " + numero;
		}
		if (suNumero == numero) {
			acabo = true;
			ganador = jugador;
			return "¡ENHORABUENA! Has adivinado el número.";
		}
		if (suNumero < numero) {
			return "El número es MAYOR que " + suNumero;
		} else {
			return "El número es MENOR que " + suNumero;
		}
	}
}
