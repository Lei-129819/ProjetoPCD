package iscot.events;
import iscot.enums.Areas;

public class SensorEvent extends Event {
    private double reading;

    public SensorEvent(Areas area, String deviceId, double reading) {
        super(area, deviceId);
        this.reading=reading;
    }

    public double getReading() {
        return reading;
    }

    @Override
    public String toString() {
        return super.toString() + " | Valor lido: " + reading;
    }
}
