package com.oocl.cultivation;

import com.oocl.cultivation.choosestrategy.SmartParkingBoyChooseLotStrategy;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    private SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        this.chooseParkStrategy = new SmartParkingBoyChooseLotStrategy();
    }

}
