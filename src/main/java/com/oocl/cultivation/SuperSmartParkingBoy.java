package com.oocl.cultivation;

import java.util.IntSummaryStatistics;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public CarTicket park(Car car) {
        if (car != null){
            IntSummaryStatistics parkingLotUsedPositionStatistics = parkingLotList.stream().mapToInt((x) -> x.getUsedParkingPosition()).summaryStatistics();//TODO:how to return ParkingLot Object
            if (super.isAllParkingLotFull(parkingLotUsedPositionStatistics)){
                super.printErrorMsg("Not enough position.");
            }else {
                CarTicket carTicket = getCarTicket(car);
                if (carTicket != null)
                    return carTicket;
            }
        }
        return null;
    }

    public CarTicket getCarTicket(Car car){
        IntSummaryStatistics parkingLotAvailableRateStatistics = parkingLotList.stream().mapToInt((x) -> (x.getCapacity()-x.getUsedParkingPosition()) % x.getCapacity()).summaryStatistics();//TODO:how to return ParkingLot Object
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getCapacity() == 0){
                continue;
            }else {
                if (isHigherAvailableRate(parkingLotAvailableRateStatistics, parkingLot)){
                    CarTicket parkingTicket = new CarTicket(car.getCarId());
                    carTicketCarHashMap.put(parkingTicket,car);
                    parkingLot.calculateParkingLotPosition();
                    return parkingTicket;
                }
            }
        }
        return null;
    }

    private boolean isHigherAvailableRate(IntSummaryStatistics parkingLotAvailableRateStatistics, ParkingLot parkingLot) {
        int rate = (parkingLot.getCapacity()-parkingLot.getUsedParkingPosition()) % parkingLot.getCapacity();
        return parkingLotAvailableRateStatistics.getMax() == rate;
    }

}
