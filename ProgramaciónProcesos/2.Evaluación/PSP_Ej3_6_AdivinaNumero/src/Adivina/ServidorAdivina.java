package Adivina;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Modelo.ObjetoCompartido;

public class ServidorAdivina {

	public static void main(String[] args) {
	    try (ServerSocket serverSocket = new ServerSocket(5000)) {

	        System.out.println("Servidor iniciado...");
	        int numeroAleatorio = (int) (Math.random() * 25) + 1;
	        System.out.println("Numero a adivinar: " + numeroAleatorio);

	        int id = 1;
	        ObjetoCompartido compartido = new ObjetoCompartido(numeroAleatorio);
	        while (true) {
	            Socket cliente = serverSocket.accept();
	            System.out.println("Cliente conectado: " + id);
	            HiloServidorAdivina hilo = new HiloServidorAdivina(cliente, compartido, id);
	            hilo.start();

	            id++;
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

}
