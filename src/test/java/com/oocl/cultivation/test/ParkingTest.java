package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingTest {
    @Test
    void should_return_parking_ticket_when_park_given_a_car() {
        //given
        String carId = "A001";
        Car car = new Car(carId);
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Ticket parkingTicket = parkingBoy.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_car_when_fetch_given_a_correct_parking_ticket() {
        //given
        Car parkingCar = new Car("A001");
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.park(parkingCar);

        //when
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        Assertions.assertEquals(parkingCar,fetchCar);
    }
}
