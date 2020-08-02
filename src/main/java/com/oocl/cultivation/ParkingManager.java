package com.oocl.cultivation;

import java.util.List;

public class ParkingManager extends ParkingBoy{

    List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
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
