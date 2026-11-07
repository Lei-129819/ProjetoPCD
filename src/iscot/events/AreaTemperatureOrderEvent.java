package iscot.events;

import iscot.enums.Areas;
import iscot.enums.AirConditioningMode;
public class AreaTemperatureOrderEvent extends HighLevelOrderEvent{

    private  AirConditioningMode mode;
    public AreaTemperatureOrderEvent(Areas area, String deviceId, AirConditioningMode mode){
        super(area, deviceId);
        this.mode= mode;
    }

    public AirConditioningMode getMode() {
        return mode;
    }

    @Override
    public String toString(){
        return "[AreaTemperatureOrderEvent]" + super.toString() + "Ligar:" + mode;
    }
}
