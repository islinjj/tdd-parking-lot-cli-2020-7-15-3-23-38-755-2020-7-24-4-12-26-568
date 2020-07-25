package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;
    private Map<CarTicket,Car> carTicketCarHashMap = new HashMap<>();

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public CarTicket park(Car car) {
        if (isAllParkingLotFull()){
            printErrorMsg("Not enough position.");
            return null;
        }
        if (getCarTicket(car) != null)
            return getCarTicket(car);
        return null;
    }

    private CarTicket getCarTicket(Car car) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getCapacity() == 0){
                continue;
            }else {
                CarTicket parkingTicket = new CarTicket(car.getCarId());
                carTicketCarHashMap.put(parkingTicket,car);
                parkingLot.countUsedParkingSpace();
                parkingLot.countCapacity();
                return parkingTicket;
            }
        }
        return null;
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

    private boolean isAllParkingLotFull() {
        boolean isAllParkingLotFull = true;
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getCapacity() != 0){
                isAllParkingLotFull = false;
                break;
            }
        }
        return isAllParkingLotFull;
    }

    private void printErrorMsg(String s) {
        System.out.print(s);
    }
}
