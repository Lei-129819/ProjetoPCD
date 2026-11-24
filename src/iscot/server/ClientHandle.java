package iscot.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.Event;
import iscot.events.RegistrationEvent;
import iscot.events.RegistrationResponse;

public class ClientHandle implements Runnable {
    private Socket socket;
    private DeviceIdGenerator idGenerator;
    private Areas area;
    private String deviceId;
    private Type type;

    public ClientHandle(Socket socket, DeviceIdGenerator idGenerator) {
        this.socket = socket;
        this.idGenerator = idGenerator;
    }

@Override
public void run() {
    try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

        Object firstMessage = in.readObject();

        if (firstMessage instanceof RegistrationEvent) {
            RegistrationEvent reg = (RegistrationEvent) firstMessage;
            this.area = reg.getArea();
            this.type = reg.getDeviceType();

            if (type==null || type== Type.DWC){
                System.out.println("[Registo Recusado] Tipo inválido: "+ type);
                out.writeObject(new RegistrationResponse(false, null));
                out.flush();
                return;
            }

            this.deviceId = idGenerator.generate(type,area);
            out.writeObject(new RegistrationResponse(true,deviceId));
            out.flush();

            System.out.println("[Registo Sucesso] Dispositivo registado: " + deviceId +
                    " | Tipo: " + type + " | Área: " + area);
        } else {
            System.err.println("[Erro] O primeiro objeto recebido não foi um RegistrationEvent!");
            return;
        }

        // 2. Ciclo contínuo de escuta de mensagens/eventos vindos deste cliente específico
        while (true) {
            Object receivedObject = in.readObject();

            if (receivedObject instanceof Event) {
                Event event = (Event) receivedObject;
                System.out.println("[Mensagem Recebida de " + deviceId + "]: " + event);

                // TODO: Aqui entrará a lógica de encaminhamento (Fase 6):
                // Repassar leituras de sensores para os decisores, ou ordens para os atuadores.
            }
        }

    } catch (Exception e) {
        System.out.println("[Servidor] Ligação encerrada com o dispositivo: " + (deviceId != null ? deviceId : socket.getRemoteSocketAddress()));
        }
    }
}
