/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {

    private int PUERTO;
    private String mensaje;
    private DatagramSocket socketUDP;
    private byte[] buffer;
    private DatagramPacket peticion;
    private SimpleDateFormat formateador;

    public String iniciarConexion(int port) {
        formateador = new SimpleDateFormat("hh:mm:ss");
        try {
            this.PUERTO = port;
            this.buffer = new byte[1024];
            //Creacion del socket
            this.socketUDP = new DatagramSocket(PUERTO);

            mensaje = "Iniciado el servidor UDP";
        } catch (SocketException ex) {
            mensaje = "ERROR al Iniciar el servidor UDP";
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    public String recibirDatos() {
        try {
            while (true) {

                //Preparo la respuesta
                this.peticion = new DatagramPacket(buffer, buffer.length);

                //Recibo el datagrama
                socketUDP.receive(peticion);

                System.out.println("Recibo la informacion del cliente");

                //Convierto lo recibido y mostrar el mensaje
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                //Obtengo el puerto y la direccion de origen
                //Sino se quiere responder, no es necesario
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                
                Date ahora = new Date();
                
                mensaje = formateador.format(ahora)+"\n";
                buffer = mensaje.getBytes();

                //creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                socketUDP.send(respuesta);

                //Envio la información
                System.out.println("Envio la informacion del cliente " + direccion + puertoCliente);

            }

        } catch (SocketException ex) {
            mensaje = "ERROR al Iniciar el servidor UDP";
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

}
