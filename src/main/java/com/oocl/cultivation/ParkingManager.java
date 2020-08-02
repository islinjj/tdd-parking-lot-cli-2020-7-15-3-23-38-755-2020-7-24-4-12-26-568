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

    public CarTicket assignParkingBoyToPark(ParkingBoy parkingBoy, Car car) {
        return parkingBoy.park(car);
    }

    public Car assignParkingBoyToFetch(ParkingBoy parkingBoy, CarTicket carTicket) {
        return parkingBoy.fetch(carTicket);
    }
}
