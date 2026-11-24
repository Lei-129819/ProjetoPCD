package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serializable;

public class RegistrationEvent extends Event implements Serializable {
    private Type deviceType;

    public RegistrationEvent(Areas area, Type type){
        super(area, null);
        this.deviceType = type;
    }

    public Type getDeviceType() {
        return deviceType;
    }

    @Override
    public String toString(){
        return "[RegistrationEvent]" + super.toString() + "Tipo" + deviceType;
    }
}
