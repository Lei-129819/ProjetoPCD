package iscot.events;
import iscot.enums.Areas;

public class SensorEvent extends Event {
    private float reading;

    public SensorEvent(Areas area, String deviceId, float reading) {
        super(area, deviceId);
        this.reading=reading;
    }

    public float getReading() {
        return reading;
    }

    @Override
    public String toString() {
        return super.toString() + " | Valor lido: " + reading;
    }
}
