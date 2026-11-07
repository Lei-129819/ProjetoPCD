package iscot.events;

import iscot.enums.Areas;

public class LightSensorEvent extends SensorEvent{

    public LightSensorEvent(Areas area, String deviceId, float Reading){
        super(area, deviceId, Reading);
    }

    @Override
    public String toString(){
        return "[LightSensorEvent]" + super.toString() + "lux";
    }
}
