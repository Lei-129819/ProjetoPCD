package iscot.events;
import iscot.client.HumiditySensor;
import iscot.enums.Areas;
public class HumiditySensorEvent extends SensorEvent{

    public HumiditySensorEvent(Areas area, String deviceId, float reading){
        super(area,deviceId,reading);
    }

    @Override
    public String toString(){
        return "[HumiditySensorEvent" + super.toString() +"%";
    }
}
