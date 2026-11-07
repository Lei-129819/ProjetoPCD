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

import iscot.client.gui.LightIcon;
import iscot.enums.Areas;
import iscot.enums.Type;

import iscot.events.AreaLightingOrderEvent;
import iscot.events.RegistrationEvent;

public class Light {

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
            String deviceId = "AtuadorLuz" + area.name();

            RegistrationEvent regEvent = new RegistrationEvent(area, deviceId,Type.ACTUATOR);
            System.out.println("A simular envio de registos" + regEvent);

            JFrame janela = new JFrame("Lâmpada em "+area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



            // Criar os componentes gráficos
            JPanel painel=new JPanel();
            painel.setLayout(new BorderLayout());
            LightIcon lâmpada = new LightIcon();
            painel.add(lâmpada,BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);


            // TODO: receive and handle messages from server

            // TODO: for first phase: create an appropriate event hardcoded locally and change the GUI accordingly
            Thread.sleep(3000);

            AreaLightingOrderEvent simularEvento = new AreaLightingOrderEvent(area, deviceId, true);
            System.out.println("Receção de evento");

            lâmpada.setAtivo(simularEvento.isTurnOn());

            Thread.sleep(3000);
            AreaLightingOrderEvent simularEvento2 = new AreaLightingOrderEvent(area, deviceId, false);
            System.out.println("A desligar");
            lâmpada.setAtivo(simularEvento2.isTurnOn());

        }
    }
}
