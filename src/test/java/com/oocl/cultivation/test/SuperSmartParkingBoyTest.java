package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SuperSmartParkingBoyTest {

    @Test
    void should_return_null_parking_ticket_when_park_given_null_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        CarTicket carTicket = superSmartParkingBoy.park(null);

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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        CarTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(carId,parkingTicket.getTicketId());
    }

    @Test
    void should_return_2_parking_tickets_when_park_given_2_cars() {
        //given
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        CarTicket parkingTicketOne = superSmartParkingBoy.park(carOne);
        CarTicket parkingTicketTwo = superSmartParkingBoy.park(carTwo);

        Assertions.assertNotNull(parkingTicketOne);
        Assertions.assertNotNull(parkingTicketTwo);
        Assertions.assertEquals(carOne.getCarId(),parkingTicketOne.getTicketId());
        Assertions.assertEquals(carTwo.getCarId(),parkingTicketTwo.getTicketId());
    }

    @Test
    void should_fetch_1_car_when_fetch_given_1_correct_parking_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        CarTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car fetchCar = superSmartParkingBoy.fetch(parkingTicket);

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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        CarTicket parkingTicketOne = superSmartParkingBoy.park(carOne);
        CarTicket parkingTicketTwo = superSmartParkingBoy.park(carTwo);

        //when
        Car fetchCarOne = superSmartParkingBoy.fetch(parkingTicketOne);
        Car fetchCarTwo = superSmartParkingBoy.fetch(parkingTicketTwo);

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertNotNull(fetchCarTwo);
        Assertions.assertEquals(carOne,fetchCarOne);
        Assertions.assertEquals(carTwo,fetchCarTwo);
    }
}
