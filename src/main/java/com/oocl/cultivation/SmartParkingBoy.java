package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLotList;

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void park(Car car) {
       parkingLotList.get(0).setUsedParkingPosition(6);
       parkingLotList.get(1).setUsedParkingPosition(5);
    }
}
