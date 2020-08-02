package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingManagerTest {

    ParkingManager parkingManager;
    ParkingLot parkingLot;
    List<ParkingLot> parkingLots;

    @BeforeEach
    void init(){
        parkingManager = new ParkingManager();
        parkingLot = new ParkingLot();
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
    }

    @Test
    void should_return_1_parking_boy_when_add_1_parking_boy_given_1_parking_boy() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);

        //when
        parkingManager.addParkingBoy(parkingBoy);

        //then
        assertEquals(1,parkingManager.getParkingBoys().size());
    }

    @Test
    void should_return_none_when_not_assign_parking_boy_given_2_parking_boys_and_1_car() {
        //given
        ParkingBoy parkingBoyOne = new ParkingBoy(1);
        ParkingBoy parkingBoyTwo = new ParkingBoy(2);
        List<ParkingBoy> parkingBoys = Arrays.asList(parkingBoyOne,parkingBoyTwo);
        Car car = new Car("A001");

        //when
        CarTicket carTicket = parkingManager.assignParkingBoy(null,car);

        //then
        assertNull(carTicket);
    }

    @Test
    void should_return_1_car_ticket_when_assign_parking_boy_given_1_car_and_2_parking_boys() {
        //given
        ParkingBoy parkingBoyOne = new ParkingBoy(parkingLots, 1);
        ParkingBoy parkingBoyTwo = new ParkingBoy(parkingLots, 2);
        List<ParkingBoy> parkingBoys = Arrays.asList(parkingBoyOne,parkingBoyTwo);
        Car car = new Car("A001");

        //when
        CarTicket carTicket = parkingManager.assignParkingBoy(parkingBoyOne,car);

        //then
        assertNotNull(carTicket);
    }
}
