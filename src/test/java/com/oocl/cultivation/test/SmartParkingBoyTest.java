package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyTest {

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

}
