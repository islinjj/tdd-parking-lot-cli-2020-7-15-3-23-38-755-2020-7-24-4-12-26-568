package com.oocl.cultivation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public void park(List<ParkingLot> parkingLotList, int carsAmount) {
        parkingLotList.get(0).setUsedParkingSpace(9);
        parkingLotList.get(1).setUsedParkingSpace(0);
    }
}
