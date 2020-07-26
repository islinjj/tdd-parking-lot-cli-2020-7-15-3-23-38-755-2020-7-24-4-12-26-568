package com.oocl.cultivation;

import java.util.IntSummaryStatistics;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public CarTicket park(Car car){
        if (car != null){
            IntSummaryStatistics parkingLotUsedPositionStatistics = super.parkingLotList.stream().mapToInt((x) -> x.getUsedParkingPosition()).summaryStatistics();//TODO:how to return ParkingLot Object
            if (parkingLotUsedPositionStatistics.getMax() != 10){
                CarTicket carTicket = getCarTicket(car, parkingLotUsedPositionStatistics);
                if (carTicket != null) return carTicket;
            } else {
                System.out.print("Not enough position.");
            }
        }
        return null;
    }

    private CarTicket getCarTicket(Car car, IntSummaryStatistics parkingLotUsedPositionStatistics) {
        for (ParkingLot parkingLot : parkingLotList){
            if (isParkingLotHasMinUsedPosition(parkingLotUsedPositionStatistics, parkingLot)){
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
