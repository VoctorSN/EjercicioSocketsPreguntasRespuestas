/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.badpals.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Random;

/**
 * @author user
 */
public class HiloServidor extends Thread {
    BufferedReader fentrada;
    PrintWriter fsalida;
    Socket socket;
    BDHelper bd;
    Random random;
    //constructor del hilo, recibe el socket cliente desde hilo primario servidor
    // recibe el socket y  el hilo se encarga ahora de crear flujos de entrada y salida con el cliente,
    // mas la conexion con la bd

    public HiloServidor(Socket s) throws IOException {// CONSTRUCTOR
        socket = s;
        //el hilo recibe el socket conectado al cliente
        // el hilo se encarga de crear flujos de entrada y salida para el socket
        fsalida = new PrintWriter(socket.getOutputStream(), true);
        fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // creamos las instancias para manejar la bd y para tener el random
        bd = new BDHelper();
        random = new Random();
    }

    public void run() {

        String cadena = "";
        System.out.println("COMUNICO CON: " + socket.toString());

        try {
            cadena = fentrada.readLine();// cuado se ejecuta espera que el cliente le pase una pregunta
        } catch (IOException e) {
            e.printStackTrace();
        } // obtener  1º respuesta del cliente si no es SALIR
        while (!cadena.equals("SALIR")) {
            // le paso la respuesta random a la pregunta y espero por otra pregunta
            List<String> respuestas = bd.getRespuestas(cadena);
            String respuesta = "NO DISPONGO DE LA RESPUESTA A LA PREGUNTA";
            if(!respuestas.isEmpty()){ // si encuentra alguna respuesta cogemos una random
                 respuesta = (respuestas).get(random.nextInt(respuestas.size()));
            }
            //pasamos al cliente la respuesta del servidor
            fsalida.println(respuesta);
            try {
                cadena = fentrada.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // CERRAMOS EL SOCKET Y LOS FLUJOS
        try {
            fsalida.close();
            fentrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("FIN CON: " + socket.toString());
    }

}