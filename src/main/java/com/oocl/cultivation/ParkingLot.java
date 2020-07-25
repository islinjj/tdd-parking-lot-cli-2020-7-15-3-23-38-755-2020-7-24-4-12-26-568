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

    public CarTicket park(Car car) {
        if (capacity == 0){
            printErrorMsg("Not enough position.");
            return null;
        }
        CarTicket parkingTicket = new CarTicket(car.getCarId());
        carTicketCarHashMap.put(parkingTicket,car);
        capacity--;
        usedParkingSpace++;
        return parkingTicket;
    }

    public Car fetch(CarTicket parkingTicket) {
        Car car = carTicketCarHashMap.get(parkingTicket);
        if (parkingTicket == null){
            printErrorMsg("Please provide your parking ticket.");
            return null;
        }
        if (car == null){
            printErrorMsg("Unrecognized parking ticket.");
        }
        carTicketCarHashMap.remove(parkingTicket);
        return car;
    }

    private void printErrorMsg(String s) {
        System.out.print(s);
    }

}
