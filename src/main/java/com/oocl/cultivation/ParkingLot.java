package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LINVI7
 * @Date 7/24/2020 4:41 PM
 * @Version 1.0
 */
public class ParkingLot {
    private int capacity = 10;
    private int usedParkingPosition = 0;
    protected Map<CarTicket,Car> carTicketCarHashMap = new HashMap<>();

    public void setUsedParkingPosition(int usedParkingPosition) {
        this.usedParkingPosition = usedParkingPosition;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getUsedParkingPosition() {
        return this.usedParkingPosition;
    }

    public CarTicket park(Car car) {
        CarTicket parkingTicket = new CarTicket(car.getCarId());
        carTicketCarHashMap.put(parkingTicket,car);
        calculateParkingLotPosition();
        return parkingTicket;
    }

    public void calculateParkingLotPosition(){
        this.capacity--;
        this.usedParkingPosition++;
    }

}
