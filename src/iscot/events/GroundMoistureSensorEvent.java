package iscot.events;
import iscot.enums.Areas;

public class GroundMoistureSensorEvent extends SensorEvent{

    public GroundMoistureSensorEvent(Areas area, String deviceId, float Reading){
        super(area, deviceId, Reading);
    }

    @Override
    public String toString(){
        return "[GroundMoistureEvent]" + super.toString() + "%";
    }
}
