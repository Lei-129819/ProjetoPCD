package iscot.client;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.SensorControl;
import iscot.enums.Areas;
import iscot.enums.Type;


import iscot.events.RegistrationEvent;
import iscot.events.GroundMoistureSensorEvent;

public class GroundMoistureSensor {

    /**
     *
     * @param server address and port
     * @param roomName: string
     * @throws IOException
     * @throws UnknownHostException
     * @throws NumberFormatException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws NumberFormatException, InterruptedException {
        if(args.length!=3) {
            System.err.println("Invalid arguments!");
        }
        else {

            Areas area = Areas.valueOf(args [2]);
            final String deviceId = "GroundMoistureSensor" + area.name();

            // TODO connect to the server

            // TODO register with the server

            RegistrationEvent regEvent = new RegistrationEvent(area,deviceId,Type.SENSOR);
            System.out.println("A simular envio de registos:" +regEvent);


            JFrame janela = new JFrame("Humidade no solo em "+area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            // Criar os componentes gráficos
            JPanel painel=new JPanel();
            painel.setLayout(new BorderLayout());
            SensorControl sensorControl= new SensorControl( "☀", "Humidade no Solo - "+area, "%",
                    0, 100, 1, 50,
                    valor -> {
                        // TODO handle change in sensor

                        GroundMoistureSensorEvent event= new GroundMoistureSensorEvent(area,deviceId, valor);

                        System.out.println("A transmitir evento: " + event);
                    });
            painel.add(sensorControl,BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);

        }
    }

}
