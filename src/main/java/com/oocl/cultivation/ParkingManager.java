package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {

    List<ParkingBoy> parkingBoys;

    public ParkingManager() {
        this.parkingBoys = new ArrayList<>();
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }
}
