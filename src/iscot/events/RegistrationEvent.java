package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serializable;

public class RegistrationEvent extends Event implements Serializable {
    private Type deviceType;

    public RegistrationEvent(Areas area, String deviceId, Type deviceType){
        super(area, deviceId);
        this.deviceType= deviceType;
    }

    public Type getDeviceType() {
        return deviceType;
    }

    @Override
    public String toString(){
        return "[RegistrationEvent]" + super.toString() + "Tipo" + deviceType;
    }
}
