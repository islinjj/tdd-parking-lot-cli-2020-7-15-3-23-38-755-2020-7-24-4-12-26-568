package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;
    private ParkingLot parkingLot;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void park(Car car) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getCapacity() == 0){
                continue;
            }else {
                parkingLot.park(car);
                break;
            }
        }
    }
}
