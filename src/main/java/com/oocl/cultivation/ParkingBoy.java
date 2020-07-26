package com.oocl.cultivation;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;
    protected Map<CarTicket,Car> carTicketCarHashMap = new HashMap<>();

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public CarTicket park(Car car) {
        if (car != null && !isAllParkingLotFull()){
            CarTicket carTicket = getCarTicket(car);
            if (carTicket != null)
                return carTicket;
        }else {
            printErrorMsg("Not enough position.");
        }
        return null;
    }

    private CarTicket getCarTicket(Car car) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getCapacity() == 0){
                continue;
            }else {
                CarTicket parkingTicket = new CarTicket(car.getCarId());
                carTicketCarHashMap.put(parkingTicket,car);
                parkingLot.countUsedParkingPosition();
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
        IntSummaryStatistics parkingLotUsedPositionStatistics = parkingLotList.stream().mapToInt((x) -> x.getUsedParkingPosition()).summaryStatistics();//TODO:how to return ParkingLot Object
        return parkingLotUsedPositionStatistics.getMin() == 10;
    }

    private void printErrorMsg(String s) {
        System.out.print(s);
    }
}
