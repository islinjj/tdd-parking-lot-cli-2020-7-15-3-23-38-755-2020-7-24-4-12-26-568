package com.oocl.cultivation;

/**
 * @Author LINVI7
 * @Date 7/24/2020 4:41 PM
 * @Version 1.0
 */
public class ParkingBoy {
    private Ticket ticket;

    public Ticket park(Car car) {
        ticket = new Ticket(car.getCarId());
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return null;
    }
}
