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
    }

}
