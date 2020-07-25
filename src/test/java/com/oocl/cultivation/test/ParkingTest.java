package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.CarTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ParkingTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    public String systemOut() {
        return outContent.toString();
    }


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
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        ParkingLot parkingLot = new ParkingLot();

        //when
        CarTicket parkingTicketOne = parkingLot.park(carOne);
        CarTicket parkingTicketTwo = parkingLot.park(carTwo);

        Assertions.assertNotNull(parkingTicketOne);
        Assertions.assertNotNull(parkingTicketTwo);
    }

    @Test
    void should_fetch_1_car_when_fetch_given_1_correct_parking_ticket() {
        //given
        Car car = new Car("A001");
        ParkingLot parkingLot = new ParkingLot();
        CarTicket parkingTicket = parkingLot.park(car);

        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals(car,fetchCar);
    }

    @Test
    void should_fetch_2_cars_when_fetch_given_2_correct_parking_tickets() {
        //given
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        ParkingLot parkingLot = new ParkingLot();
        CarTicket parkingTicketOne = parkingLot.park(carOne);
        CarTicket parkingTicketTwo = parkingLot.park(carTwo);

        //when
        Car fetchCarOne = parkingLot.fetch(parkingTicketOne);
        Car fetchCarTwo = parkingLot.fetch(parkingTicketTwo);

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertNotNull(fetchCarTwo);
        Assertions.assertEquals(carOne,fetchCarOne);
        Assertions.assertEquals(carTwo,fetchCarTwo);
    }

    @Test
    void should_fetch_1_correct_car_when_fetch_1_car_from_2_cars_given_2_cars() {
        //given
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        ParkingLot parkingLot = new ParkingLot();
        CarTicket parkingTicketOne = parkingLot.park(carOne);
        CarTicket parkingTicketTwo = parkingLot.park(carTwo);

        //when
        Car fetchCarOne = parkingLot.fetch(parkingTicketOne);

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertEquals(carOne,fetchCarOne);
    }

    @Test
    void should_fetch_null_when_fetch_given_no_ticket() {
        //given
        Car car = new Car("A001");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(car);

        //when
        Car fetchCar = parkingLot.fetch(null);

        //then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_print_error_msg_when_fetch_given_no_ticket() {
        //given
        Car car = new Car("A001");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(car);

        //when
        parkingLot.fetch(null);

        //then
        Assertions.assertEquals("Please provide your parking ticket.",systemOut());
    }

    @Test
    void should_fetch_null_when_fetch_given_wrong_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        // when
        //when
        Car fetchCar = parkingLot.fetch(wrongParkingTicket);

        // then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_wrong_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        // when
        parkingLot.fetch(wrongParkingTicket);

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", systemOut());
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

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A001");
        CarTicket parkingTicket = parkingLot.park(car);

        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);
        parkingLot.fetch(parkingTicket);

        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals("Unrecognized parking ticket.", systemOut());
    }

    @Test
    void should_return_null_parking_ticket_when_park_with_no_position_given_cars_and_capacity() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        int capacity = 10;
        for (int i = 1; i <= capacity; i++){
            parkingLot.park(new Car("A00" + Integer.valueOf(i)));
        }

        //when
        CarTicket parkingTicket = parkingLot.park(new Car("A0011"));

        //then
        Assertions.assertNull(parkingTicket);
    }

    @Test
    void should_print_error_msg_when_park_with_no_position_given_cars_and_capacity() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        int capacity = 10;
        for (int i = 1; i <= capacity; i++){
            parkingLot.park(new Car("A00" + Integer.valueOf(i)));
        }

        //when
        parkingLot.park(new Car("A0011"));

        //then
        Assertions.assertEquals("Not enough position.",systemOut());
    }

}
