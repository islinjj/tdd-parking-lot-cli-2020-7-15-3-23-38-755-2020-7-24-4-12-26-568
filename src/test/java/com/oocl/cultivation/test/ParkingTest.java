package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.CarTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingTest {
    @Test
    void should_return_parking_ticket_when_park_given_a_car() {
        //given
        String carId = "A001";
        Car car = new Car(carId);
        ParkingLot parkingLot = new ParkingLot();

        //when
        CarTicket parkingTicket = parkingLot.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_2_parking_ticket_when_park_given_two_car() {
        //given
        Car parkingCarOne = new Car("A001");
        Car parkingCarTwo = new Car("A002");
        ParkingLot parkingLot = new ParkingLot();

        //when
        CarTicket parkingTicketOne = parkingLot.park(parkingCarOne);
        CarTicket parkingTicketTwo = parkingLot.park(parkingCarTwo);

        Assertions.assertNotNull(parkingTicketOne);
        Assertions.assertNotNull(parkingTicketTwo);
    }

    @Test
    void should_fetch_a_car_when_fetch_given_a_correct_parking_ticket() {
        //given
        Car parkingCar = new Car("A001");
        ParkingLot parkingLot = new ParkingLot();
        CarTicket parkingTicket = parkingLot.park(parkingCar);

        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals(parkingCar,fetchCar);
    }

    @Test
    void should_fetch_2_car_when_fetch_given_2_correct_parking_ticket() {
        //given
        Car parkingCarOne = new Car("A001");
        Car parkingCarTwo = new Car("A002");
        ParkingLot parkingLot = new ParkingLot();
        CarTicket parkingTicketOne = parkingLot.park(parkingCarOne);
        CarTicket parkingTicketTwo = parkingLot.park(parkingCarTwo);

        //when
        Car fetchCarOne = parkingLot.fetch(parkingTicketOne);
        Car fetchCarTwo = parkingLot.fetch(parkingTicketTwo);

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertNotNull(fetchCarTwo);
        Assertions.assertEquals(parkingCarOne,fetchCarOne);
        Assertions.assertEquals(parkingCarTwo,fetchCarTwo);
    }

    @Test
    void should_fetch_a_correct_car_when_fetch_a_car_from_2_car_given_2_car() {
        //given
        Car parkingCarOne = new Car("A001");
        Car parkingCarTwo = new Car("A002");
        ParkingLot parkingLot = new ParkingLot();
        CarTicket parkingTicketOne = parkingLot.park(parkingCarOne);
        CarTicket parkingTicketTwo = parkingLot.park(parkingCarTwo);

        //when
        Car fetchCarOne = parkingLot.fetch(parkingTicketOne);

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertEquals(parkingCarOne,fetchCarOne);
    }
}
