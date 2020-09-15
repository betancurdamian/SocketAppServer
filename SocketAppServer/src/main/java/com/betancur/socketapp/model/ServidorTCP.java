/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ServidorTCP {
        private ServerSocket servidor;
        private Socket sc;
        private DataInputStream in;
        private DataOutputStream out;
        private int PUERTO;
        private String mensaje;
        private SimpleDateFormat formateador;
        
    public String iniciarConexion(int port) {        
        formateador = new SimpleDateFormat("hh:mm:ss");
        
        //puerto de nuestro servidor
        this.PUERTO = port;
 
        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            mensaje= "Iniciado el servidor TCP";             
 
        } catch (IOException ex) {
            this.mensaje = "Error al iniciar el Servidor"; 
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
    
    public String recibirMensaje() {  
        try {
            //Siempre estara escuchando peticiones
            while (true) {
 
                //Espero a que un cliente se conecte
                sc = servidor.accept();
 
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
 
                //Leo el mensaje que me envia
                this.mensaje = in.readUTF();
 
                System.out.println(mensaje);
 
                Date ahora = new Date();
                
                
                //Le envio un mensaje
                out.writeUTF(formateador.format(ahora));
 
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");
 
            }
 
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
 
    
 
}
