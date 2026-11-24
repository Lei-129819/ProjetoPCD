package iscot.events;

import java.io.Serializable;

public class RegistrationResponse implements Serializable {
    private boolean accepted;
    private String deviceId;

    public RegistrationResponse(boolean accepted, String deviceId){
        this.accepted=accepted;
        this.deviceId=deviceId;
    }

    public boolean isAccepted(){ return accepted;}
    public String getDeviceId(){return deviceId;}
}
