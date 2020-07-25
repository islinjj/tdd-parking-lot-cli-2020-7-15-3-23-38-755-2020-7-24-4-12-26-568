package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LINVI7
 * @Date 7/24/2020 4:41 PM
 * @Version 1.0
 */
public class ParkingLot {
    private Map<CarTicket,Car> parkedCars = new HashMap<>();
    private int capacity = 10;

    public CarTicket park(Car car) {
        if (parkedCars.size() == capacity){
            return null;
        }
        CarTicket parkingTicket = new CarTicket(car.getCarId());
        parkedCars.put(parkingTicket,car);
        return parkingTicket;
    }

    public Car fetch(CarTicket parkingTicket) {
        Car car = parkedCars.get(parkingTicket);
        if (car == null){
            System.out.print("Unrecognized parking ticket.");
            return null;
        }
        parkedCars.remove(parkingTicket);
        return car;
    }
}
