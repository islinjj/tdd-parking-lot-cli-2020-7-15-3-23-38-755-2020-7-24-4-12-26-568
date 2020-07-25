package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LINVI7
 * @Date 7/24/2020 4:41 PM
 * @Version 1.0
 */
public class ParkingLot {
    private Map<CarTicket,Car> parkedCarts = new HashMap<>();

    public CarTicket park(Car car) {
        CarTicket parkingTicket = new CarTicket(car.getCarId());
        parkedCarts.put(parkingTicket,car);
        return parkingTicket;
    }

    public Car fetch(CarTicket parkingTicket) {
        return parkedCarts.get(parkingTicket);
    }
}
