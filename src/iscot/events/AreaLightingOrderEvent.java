package iscot.events;
import iscot.enums.Areas;

public class AreaLightingOrderEvent extends HighLevelOrderEvent {

    public boolean turnOn;

    public AreaLightingOrderEvent(Areas area, String deviceId, boolean turnOn) {
        super(area, deviceId);
        this.turnOn = turnOn;
    }

    public boolean isTurnOn(){
        return turnOn;
    }

    @Override
    public String toString(){
        return "[AreaLightingOrderEvent]" + super.toString() + "|Acender" + turnOn;
    }

}
