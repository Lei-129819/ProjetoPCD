package iscot.events;

import iscot.enums.Areas;
import java.io.Serializable;

/**
 * Classe base de topo para todos os eventos do sistema IscoT.
 */
public abstract class Event {
    
    private Areas area;
    private String deviceId;

    /**
     * Construtor da classe abstrata.
     */
    public Event(Areas area, String deviceId) {
        this.area = area;
        this.deviceId = deviceId;
    }

    public Areas getArea() {
        return area;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String toString() {
        return "Area: " + area + " | DeviceID: " + deviceId;
    }

}