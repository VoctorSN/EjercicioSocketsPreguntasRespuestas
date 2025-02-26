/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.badpals.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author user
 */
public class Cliente {


    public static void main(String[] args) throws IOException {

        //Creamos la clase Socket para que comunique con el servidor
        Socket Cliente = new Socket("localhost", 6000);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        BufferedReader fentrada = new BufferedReader
                (new InputStreamReader(Cliente.getInputStream()));

        // FLUJO PARA  MI ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String pregunta, respuesta = "";

        do {
            System.out.println("PREGUNTAME ALGO (Para finalizar escribe SALIR):");

            pregunta = in.readLine();
            fsalida.println(pregunta);// leo la pregunta y se la mando al servidor

            respuesta = fentrada.readLine(); //INTENTO LEER LA RESPUESTA DEL SERVIDOR
            if(respuesta != null){
                System.out.println("LA RESPUESTA DEL SERVIDOR ES: " + respuesta); // ENSEÑO LA RESPUESTA
            }

            } while (!pregunta.equals("SALIR")); // CUANDO LEA SALIR SALE DEL BUCLE

        // CIERRA LOS FLUJOS
        fsalida.close();
        fentrada.close();
        in.close();
        Cliente.close();
        System.out.println("Fin del proceso con el servidor... ");
    }

}