package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import com.oocl.cultivation.exception.ParkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SuperSmartParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    public String systemOut() {
        return outContent.toString();
    }

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

    @Test
    void should_fetch_1_correct_car_when_fetch_1_car_from_2_cars_given_2_cars() {
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

        //then
        Assertions.assertNotNull(fetchCarOne);
        Assertions.assertEquals(carOne,fetchCarOne);
    }

    @Test
    void should_fetch_null_when_fetch_given_no_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        superSmartParkingBoy.park(car);

        //when
        Car fetchCar = superSmartParkingBoy.fetch(null);

        //then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_print_error_msg_when_fetch_given_no_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        superSmartParkingBoy.park(car);

        //when
        superSmartParkingBoy.fetch(null);

        //then
        Assertions.assertEquals("Please provide your parking ticket.",systemOut());
    }

    @Test
    void should_fetch_null_when_fetch_given_wrong_ticket() {
        // given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        // when
        //when
        Car fetchCar = superSmartParkingBoy.fetch(wrongParkingTicket);

        // then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_wrong_ticket() {
        // given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        // when
        superSmartParkingBoy.fetch(wrongParkingTicket);

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", systemOut());
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_used_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Car car = new Car("A001");
        CarTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car fetchCar = superSmartParkingBoy.fetch(parkingTicket);
        superSmartParkingBoy.fetch(parkingTicket);

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals("Unrecognized parking ticket.", systemOut());
    }

    @Test
    void should_print_error_msg_when_park_with_no_position_given_cars_and_capacity() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        int capacity = 10;
        for (int i = 1; i <= capacity; i++){
            superSmartParkingBoy.park(new Car("A00" + i));
        }

        //when
        Throwable throwable = Assertions.assertThrows(ParkException.class, () -> superSmartParkingBoy.park(new Car("A0011")));

        //then
        Assertions.assertEquals("Not enough position.",throwable.getMessage());
    }

    @Test
    void should_return_parking_lot_one_add_1_car_when_park_given_1_car_and_same_available_position_rate_parking_lot() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLotOne.setUsedParkingPosition(3);
        parkingLotTwo.setUsedParkingPosition(3);
        parkingLotList.add(parkingLotOne);
        parkingLotList.add(parkingLotTwo);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        superSmartParkingBoy.park(car);

        //then
        Assertions.assertEquals(4,parkingLotOne.getUsedParkingPosition());
        Assertions.assertEquals(3,parkingLotTwo.getUsedParkingPosition());
    }

    @Test
    void should_return_parking_lot_two_7_cars_and_parking_lot_5_cars_when_park_given_2_car_and_higher_available_position_rate_parking_lot_two() {
        //given
        Car carOne = new Car("A001");
        Car carTwo = new Car("A002");
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLotOne.setUsedParkingPosition(6);
        parkingLotTwo.setUsedParkingPosition(4);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotOne);
        parkingLotList.add(parkingLotTwo);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        superSmartParkingBoy.park(carOne);
        superSmartParkingBoy.park(carTwo);

        //then
        Assertions.assertEquals(7,parkingLotOne.getUsedParkingPosition());
        Assertions.assertEquals(5,parkingLotTwo.getUsedParkingPosition());
    }
}
