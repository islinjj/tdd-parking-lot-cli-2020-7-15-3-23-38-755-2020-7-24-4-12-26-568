package com.oocl.cultivation;

import java.util.IntSummaryStatistics;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public CarTicket park(Car car){
        if (car == null){
            return null;
        }
        IntSummaryStatistics intSummaryStatistics = super.parkingLotList.stream().mapToInt((x) -> x.getUsedParkingPosition()).summaryStatistics();//TODO:how to return ParkingLot Object
        CarTicket carTicket = getCarTicket(car, intSummaryStatistics);
        if (carTicket != null) return carTicket;
        return null;
    }

    private CarTicket getCarTicket(Car car, IntSummaryStatistics intSummaryStatistics) {
        for (ParkingLot parkingLot : parkingLotList){
            if (isParkingLotHasMinUsedPosition(intSummaryStatistics, parkingLot)){
                CarTicket carTicket = new CarTicket(car.getCarId());
                super.carTicketCarHashMap.put(carTicket,car);
                parkingLot.countCapacity();
                parkingLot.countUsedParkingPosition();
                return carTicket;
            }
        }
        return null;
    }

    private boolean isParkingLotHasMinUsedPosition(IntSummaryStatistics intSummaryStatistics, ParkingLot parkingLot) {
        return parkingLot.getUsedParkingPosition() == intSummaryStatistics.getMin();
    }
}
