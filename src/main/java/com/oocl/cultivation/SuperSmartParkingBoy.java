package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public CarTicket park(Car car) {
        if (car != null){
            if (super.isAllParkingLotFull()){
                System.out.print("Not enough position.");
            }else {
                CarTicket carTicket = super.getCarTicket(car);
                if (carTicket != null)
                    return carTicket;
            }
        }
        return null;
    }
}
