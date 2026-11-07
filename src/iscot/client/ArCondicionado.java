package iscot.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.ACIcon;
import iscot.enums.Areas;
import iscot.enums.Type;


import iscot.events.AreaTemperatureOrderEvent;
import iscot.events.RegistrationEvent;

import static iscot.enums.AirConditioningMode.*;

public class ArCondicionado {

    /**
     *
     * @param server address and port
     * @param roomName: string
     * @throws IOException
     * @throws UnknownHostException
     * @throws NumberFormatException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
        if(args.length!=3) {
            System.err.println("Invalid arguments!");
        }
        else {
            // TODO connect to server

            // TODO register with the server
            Areas area=Areas.valueOf(args[2]);
            String deviceId = "ArCondicionado" + area.name();

            RegistrationEvent regEvent = new RegistrationEvent(area, deviceId,Type.ACTUATOR);
            System.out.println("A simular envio de registos" + regEvent);

            JFrame janela = new JFrame("AC em "+ area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



            // Criar os componentes gráficos
            JPanel painel=new JPanel();
            painel.setLayout(new BorderLayout());
            ACIcon arcondicionado = new ACIcon();
            painel.add(arcondicionado,BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);


            // TODO: receive and handle messages from server

            // TODO: for first phase: create an appropriate event hardcoded locally and change the GUI accordingly
            Thread.sleep(3000);

            AreaTemperatureOrderEvent simularEvento = new AreaTemperatureOrderEvent(area, deviceId,Aquecimento);
            System.out.println("Receção de evento");

            arcondicionado.setModo(simularEvento.getMode());

            Thread.sleep(3000);
            AreaTemperatureOrderEvent simularEvento2 = new AreaTemperatureOrderEvent(area, deviceId, Desligado);
            System.out.println("A desligar");
            arcondicionado.setModo(simularEvento2.getMode());

        }
    }
}
