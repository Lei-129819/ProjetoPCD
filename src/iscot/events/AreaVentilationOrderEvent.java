package iscot.events;

import iscot.enums.Areas;

public class AreaVentilationOrderEvent extends HighLevelOrderEvent{

    private boolean turnOn;
    public AreaVentilationOrderEvent(Areas area, String deviceId, boolean turnOn){
        super(area, deviceId);
        this.turnOn=turnOn;
    }

    public boolean isTurnOn() {
        return turnOn;
    }

    @Override
    public String toString(){
        return "[AreaVentilationOrderEvent]" + super.toString() + "Ligar:" + turnOn;
    }
}
