package edu.badpals.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPreguntas {

    public static void main(String[] args) throws IOException {
        // Creamos el servidor en el puerto 6000
        ServerSocket servidor;
        servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado.....");

        //Hasta que se pare su ejecucion forzosamente va a estar aceptando clientes y creando un hilo para cada uno
        while (true) {
            Socket cliente = new Socket();
            cliente = servidor.accept();
            HiloServidor hilo = new HiloServidor(cliente);
            hilo.start();
        }
    }

}