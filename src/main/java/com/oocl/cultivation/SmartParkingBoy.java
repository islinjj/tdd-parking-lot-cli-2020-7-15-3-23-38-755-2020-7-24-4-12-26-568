package com.oocl.cultivation;

import java.util.IntSummaryStatistics;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public CarTicket park(Car car){
        IntSummaryStatistics intSummaryStatistics = super.parkingLotList.stream().mapToInt((x) -> x.getUsedParkingPosition()).summaryStatistics();//TODO:how to return ParkingLot Object
        for (ParkingLot parkingLot : parkingLotList){
            if (parkingLot.getUsedParkingPosition() == intSummaryStatistics.getMin()){
                super.carTicketCarHashMap.put(new CarTicket(car.getCarId()),car);
                parkingLot.countCapacity();
                parkingLot.countUsedParkingPosition();
            }
        }
        return null;
    }
}
