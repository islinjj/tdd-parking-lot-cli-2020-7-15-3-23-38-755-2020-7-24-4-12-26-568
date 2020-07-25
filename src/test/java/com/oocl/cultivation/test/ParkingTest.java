package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.CarTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingTest {
    @Test
    void should_return_parking_ticket_when_park_given_1_car() {
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
    void should_return_2_parking_tickets_when_park_given_2_cars() {
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
    void should_fetch_1_car_when_fetch_given_1_correct_parking_ticket() {
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
    void should_fetch_2_cars_when_fetch_given_2_correct_parking_tickets() {
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
    void should_fetch_1_correct_car_when_fetch_1_car_from_2_cars_given_2_cars() {
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

    @Test
    void should_fetch_null_when_fetch_given_no_ticket() {
        //given
        Car parkingCar = new Car("A001");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(parkingCar);

        //when
        Car fetchCar = parkingLot.fetch(null);

        //then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_fetch_null_when_fetch_given_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        //when
        Car fetchCar = parkingLot.fetch(wrongParkingTicket);

        //then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_fetch_null_when_fetch_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A001");
        CarTicket parkingTicket = parkingLot.park(car);

        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);
        Car fetchSameCarAgain = parkingLot.fetch(parkingTicket);

        Assertions.assertNotNull(fetchCar);
        Assertions.assertNull(fetchSameCarAgain);
    }
}
