package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyTest {
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


}
