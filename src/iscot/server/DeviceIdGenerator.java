package iscot.server;

import iscot.enums.Areas;
import iscot.enums.Type;

public class DeviceIdGenerator {
    private int nextId=1;

    public synchronized String generate(Type type, Areas area){
        int id =nextId;
        nextId++;
        return type + "-" + area + "-" + id;
    }
}
