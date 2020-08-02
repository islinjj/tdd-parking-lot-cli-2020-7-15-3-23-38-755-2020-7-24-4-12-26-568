package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import com.oocl.cultivation.exception.FetchException;
import com.oocl.cultivation.exception.ParkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyTest {


    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    public String systemOut() {
        return outContent.toString();
    }

    @Test
    void should_return_parking_lot_two_has_5_cars_when_park_given_parking_lot_one_has_more_car_and_1_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLotOne.setUsedParkingPosition(6);
        parkingLotTwo.setUsedParkingPosition(4);
        parkingLotList.add(parkingLotOne);
        parkingLotList.add(parkingLotTwo);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car car = new Car("A001");

        //when
        smartParkingBoy.park(car);

        //given
        Assertions.assertEquals(6,parkingLotOne.getUsedParkingPosition());
        Assertions.assertEquals(5,parkingLotTwo.getUsedParkingPosition());
    }

    @Test
    void should_return_null_parking_ticket_when_park_given_null_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        CarTicket carTicket = smartParkingBoy.park(null);

        //then
        Assertions.assertNull(carTicket);
    }

    @Test
    void should_return_parking_ticket_when_park_given_1_car() {
        //given
        String carId = "A001";
        Car car = new Car(carId);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        CarTicket parkingTicket = smartParkingBoy.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_2_parking_tickets_when_park_given_2_cars() {
        //given
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        CarTicket parkingTicketOne = smartParkingBoy.park(carOne);
        CarTicket parkingTicketTwo = smartParkingBoy.park(carTwo);

        Assertions.assertNotNull(parkingTicketOne);
        Assertions.assertNotNull(parkingTicketTwo);
    }

    @Test
    void should_fetch_null_when_fetch_given_no_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        smartParkingBoy.park(car);

        //when
        Car fetchCar = smartParkingBoy.fetch(null);

        //then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_fetch_1_car_when_fetch_given_1_correct_parking_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        CarTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car fetchCar = smartParkingBoy.fetch(parkingTicket);

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals(car,fetchCar);
    }

    @Test
    void should_fetch_2_cars_when_fetch_given_2_correct_parking_tickets() {
        //given
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        CarTicket parkingTicketOne = smartParkingBoy.park(carOne);
        CarTicket parkingTicketTwo = smartParkingBoy.park(carTwo);

        //when
        Car fetchCarOne = smartParkingBoy.fetch(parkingTicketOne);
        Car fetchCarTwo = smartParkingBoy.fetch(parkingTicketTwo);

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
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        CarTicket parkingTicketOne = smartParkingBoy.park(carOne);
        CarTicket parkingTicketTwo = smartParkingBoy.park(carTwo);

        //when
        Car fetchCarOne = smartParkingBoy.fetch(parkingTicketOne);

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertEquals(carOne,fetchCarOne);
    }

    @Test
    void should_print_error_msg_when_fetch_given_no_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        smartParkingBoy.park(car);

        //when
        smartParkingBoy.fetch(null);

        //then
        Assertions.assertEquals("Please provide your parking ticket.",systemOut());
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_wrong_ticket() {
        // given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        // when
        Throwable throwable = Assertions.assertThrows(FetchException.class, () -> smartParkingBoy.fetch(wrongParkingTicket));

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", throwable.getMessage());
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_used_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car car = new Car("A001");
        CarTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car fetchCar = smartParkingBoy.fetch(parkingTicket);
        Throwable throwable = Assertions.assertThrows(FetchException.class, () -> smartParkingBoy.fetch(parkingTicket));

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals("Unrecognized parking ticket.", throwable.getMessage());
    }

    @Test
    void should_print_error_msg_when_park_with_no_position_given_cars_and_capacity() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        int capacity = 10;
        for (int i = 1; i <= capacity; i++){
            smartParkingBoy.park(new Car("A00" + i));
        }

        //when
        Throwable throwable = Assertions.assertThrows(ParkException.class, () -> smartParkingBoy.park(new Car("A0011")));

        //then
        Assertions.assertEquals("Not enough position.",throwable.getMessage());
    }
}
