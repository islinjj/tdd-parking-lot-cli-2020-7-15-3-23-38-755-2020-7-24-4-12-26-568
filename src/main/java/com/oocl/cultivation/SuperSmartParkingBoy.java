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
                CarTicket carTicket = super.getCarTicket(car);
                if (carTicket != null)
                    return carTicket;
            }
        }
        return null;
    }
}
