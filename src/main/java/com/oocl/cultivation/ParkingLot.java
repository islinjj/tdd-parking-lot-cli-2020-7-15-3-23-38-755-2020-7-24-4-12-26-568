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
        CarTicket carTicket = new CarTicket(car.getCarId());
        parkedCarts.put(carTicket,car);
        return carTicket;
    }

    public Car fetch(CarTicket ticket) {
        return parkedCarts.get(ticket);
    }
}
