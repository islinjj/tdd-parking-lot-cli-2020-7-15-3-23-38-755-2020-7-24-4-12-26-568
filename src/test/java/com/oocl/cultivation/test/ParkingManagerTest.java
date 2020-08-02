package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ParkingManagerTest {

    ParkingManager parkingManager;
    ParkingLot parkingLot;
    List<ParkingLot> parkingLots;

    @BeforeEach
    void init(){
        parkingLot = new ParkingLot();
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingManager = new ParkingManager(parkingLots);
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
        CarTicket carTicket = parkingManager.assignParkingBoyToPark(null,car);

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
        CarTicket carTicket = parkingManager.assignParkingBoyToPark(parkingBoyOne,car);

        //then
        assertNotNull(carTicket);
    }

    @Test
    void should_return_1_car_when_assign_parking_boy_given_1_car_ticket_and_1_parking_boys() {
        //given
        ParkingBoy parkingBoyOne = new ParkingBoy(parkingLots, 1);
        CarTicket carTicket = parkingManager.assignParkingBoyToPark(parkingBoyOne,new Car("A001"));

        //when
        Car car = parkingManager.assignParkingBoyToFetch(parkingBoyOne,carTicket);

        //then
        assertNotNull(car);
    }

    @Test
    void should_return_parking_ticket_when_park_given_1_car() {
        //given
        String carId = "A001";
        Car car = new Car(carId);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingLotList);

        //when
        CarTicket parkingTicket = parkingManager.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_fetch_1_car_when_fetch_given_1_correct_parking_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingLotList);
        CarTicket parkingTicket = parkingManager.park(car);

        //when
        Car fetchCar = parkingManager.fetch(parkingTicket);

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals(car,fetchCar);
    }
}
