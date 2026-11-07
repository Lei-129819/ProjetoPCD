package iscot.events;

import iscot.enums.Areas;
import java.io.Serializable;

/**
 * Classe base de topo para todos os eventos do sistema IscoT.
 */
public abstract class Event implements Serializable {
    
    // Necessário porque vamos enviar estes objetos por canais de rede mais tarde
    private static final long serialVersionUID = 1L;
    
    // Propriedades comuns a TODOS os eventos do sistema
    private Areas area;
    private String deviceId;

    /**
     * Construtor da classe abstrata.
     */
    public Event(Areas area, String deviceId) {
        this.area = area;
        this.deviceId = deviceId;
    }

    // Getters para podermos ler os dados dos eventos mais tarde
    public Areas getArea() {
        return area;
    }

    public String getDeviceId() {
        return deviceId;
    }

    // O toString ajuda-nos muito nesta fase intermédia a imprimir os eventos na consola
    @Override
    public String toString() {
        return "Area: " + area + " | DeviceID: " + deviceId;
    }

    //aaaaaaaa nova
}