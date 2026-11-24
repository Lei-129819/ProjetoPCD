package iscot.client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.LightIcon;
import iscot.client.gui.SensorControl;
import iscot.enums.Areas;
import iscot.enums.Type;


import iscot.events.RegistrationEvent;
import iscot.events.HumiditySensorEvent;
import iscot.events.RegistrationResponse;

public class HumiditySensor {

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
		if (args.length != 3) {
			System.err.println("Invalid arguments!");
		} else {
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			Areas area = Areas.valueOf(args[2]);
			try {
				Socket socket = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

				System.out.println("[Sensor] Ligado com sucesso ao servidor em " + host + ":" + port);

				RegistrationEvent regEvent = new RegistrationEvent(area, Type.SENSOR);
				out.writeObject(regEvent);
				out.flush();

				RegistrationResponse resp = (RegistrationResponse) in.readObject();
				if(!resp.isAccepted()){
					System.err.println("[Sensor] Registo recusado pelo Servidor");
					socket.close();
					return;
				}

				final String deviceId = resp.getDeviceId();
				System.out.println("[Sensor] Registado com id:" + deviceId);

				JFrame janela = new JFrame("Humidade em " + area + " (" + deviceId + ")");
				janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


				// Criar os componentes gráficos
				JPanel painel = new JPanel();
				painel.setLayout(new BorderLayout());
				SensorControl sensorControl = new SensorControl("☀", "Humidade - " + area, "%",
						0, 100, 1, 50,
						valor -> {

						try{
							HumiditySensorEvent event = new HumiditySensorEvent(area,deviceId, valor);

							out.writeObject(event);
							out.flush();
							System.out.println("Evento enviado" + event );
						} catch (IOException e) {
							System.err.println("[Erro] Falha ao enviar evento pelo socket: " + e.getMessage());
							}

						});
				painel.add(sensorControl, BorderLayout.CENTER);
				janela.setContentPane(painel);
				janela.setLocation(600, 400);
				janela.setMinimumSize(janela.getSize());
				janela.pack();
				janela.setVisible(true);

			} catch (IOException | ClassNotFoundException e){
				System.err.println("[Erro crítico] Não foi possível ligar/registar ao servidor: " + e.getMessage());
			}
		}
	}
}
