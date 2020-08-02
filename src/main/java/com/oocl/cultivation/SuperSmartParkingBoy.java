package com.oocl.cultivation;

import com.oocl.cultivation.choosestrategy.SuperSmartParkingBoyChooseLotStrategy;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        this.chooseParkStrategy = new SuperSmartParkingBoyChooseLotStrategy();
    }

}
