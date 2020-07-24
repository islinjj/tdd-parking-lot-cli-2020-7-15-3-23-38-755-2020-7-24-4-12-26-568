package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
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
        String parkingTicket = parkingBoy.park(car);

        //then
        Assertions.assertEquals(carId,parkingTicket);
    }
}
