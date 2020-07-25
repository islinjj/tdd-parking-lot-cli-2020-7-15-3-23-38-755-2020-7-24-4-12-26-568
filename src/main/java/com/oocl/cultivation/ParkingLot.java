package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LINVI7
 * @Date 7/24/2020 4:41 PM
 * @Version 1.0
 */
public class ParkingLot {
    private Map<CarTicket,Car> carTicketCarHashMap = new HashMap<>();
    private int capacity = 10;
    private int usedParkingSpace = 0;

    public int getCapacity() {
        return capacity;
    }

    public void setUsedParkingSpace(int usedParkingSpace) {
        this.usedParkingSpace = usedParkingSpace;
    }

    public int getUsedParkingSpace() {
        return usedParkingSpace;
    }

    public void countCapacity() {
        this.capacity--;
    }
    public void countUsedParkingSpace(){
        this.usedParkingSpace++;
    }
}
