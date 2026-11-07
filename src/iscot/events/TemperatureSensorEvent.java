package iscot.events;
import iscot.enums.Areas;
public class TemperatureSensorEvent extends SensorEvent{

    public TemperatureSensorEvent(Areas area, String deviceId, float Reading){
        super(area, deviceId, Reading);
    }

    @Override
    public String toString(){
        return "[TemperatureSensorEvent]" + super.toString() + "ºC";
    }
}
