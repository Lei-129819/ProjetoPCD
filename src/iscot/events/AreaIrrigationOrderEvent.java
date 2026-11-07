package iscot.events;

import iscot.enums.Areas;

public class AreaIrrigationOrderEvent extends HighLevelOrderEvent{

    private boolean turnOn;

    public AreaIrrigationOrderEvent(Areas area,String deviceId, boolean turnOn){
        super(area, deviceId);
        this.turnOn=turnOn;
    }

    public boolean isTurnOn() {
        return turnOn;
    }

    @Override
    public String toString(){
        return "[AreaIrrigationOrderEvent]" + super.toString() + "Ligar:" + turnOn;
    }
}
