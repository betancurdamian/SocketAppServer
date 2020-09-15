/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.controller;

import com.betancur.socketapp.model.ServidorTCP;
import com.betancur.socketapp.model.ServidorUDP;
import com.betancur.socketapp.view.JPServer;
import java.awt.Color;

/**
 *
 * @author Ariel
 */
public class ControllerServerDayTime {

    JPServer vista;
    private int port;

    public ControllerServerDayTime(JPServer vista, int port) {
        this.vista = vista;
        this.port = port;
    }

    public void conectar(int seleccion) throws InterruptedException {

        if (seleccion == 1) {
            ServidorTCP servidorTCP = new ServidorTCP();

            vista.getJtp_consol().setEditable(true);

            vista.appendToPane(vista.getJtp_consol(), servidorTCP.iniciarConexion(port), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), servidorTCP.recibirMensaje(), Color.blue);

            vista.getJtp_consol().setEditable(false);

        } else {
            ServidorUDP servidorUDP = new ServidorUDP();

            vista.getJtp_consol().setEditable(true);

            vista.appendToPane(vista.getJtp_consol(), servidorUDP.iniciarConexion(port), Color.blue);
            vista.getJtp_consol().setEditable(false);
            vista.appendToPane(vista.getJtp_consol(), servidorUDP.recibirDatos(), Color.blue);

        }
    }
}
