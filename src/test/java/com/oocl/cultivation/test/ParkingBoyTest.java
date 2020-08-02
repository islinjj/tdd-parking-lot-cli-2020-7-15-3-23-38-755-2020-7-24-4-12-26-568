package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import com.oocl.cultivation.exception.FetchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class ParkingBoyTest {

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        CarTicket carTicket = parkingBoy.park(null);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        CarTicket parkingTicket = parkingBoy.park(car);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        CarTicket parkingTicketOne = parkingBoy.park(carOne);
        CarTicket parkingTicketTwo = parkingBoy.park(carTwo);

        Assertions.assertNotNull(parkingTicketOne);
        Assertions.assertNotNull(parkingTicketTwo);
    }

    @Test
    void should_fetch_1_car_when_fetch_given_1_correct_parking_ticket() {
        //given
        Car car = new Car("A001");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        CarTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        CarTicket parkingTicketOne = parkingBoy.park(carOne);
        CarTicket parkingTicketTwo = parkingBoy.park(carTwo);

        //when
        Car fetchCarOne = parkingBoy.fetch(parkingTicketOne);
        Car fetchCarTwo = parkingBoy.fetch(parkingTicketTwo);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        CarTicket parkingTicketOne = parkingBoy.park(carOne);
        CarTicket parkingTicketTwo = parkingBoy.park(carTwo);

        //when
        Car fetchCarOne = parkingBoy.fetch(parkingTicketOne);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(null);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car);

        //when
        parkingBoy.fetch(null);

        //then
        Assertions.assertEquals("Please provide your parking ticket.",systemOut());
    }

    @Test
    void should_fetch_null_when_fetch_given_wrong_ticket() {
        // given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        //when
        Car fetchCar;
        try{
            fetchCar = parkingBoy.fetch(wrongParkingTicket);
        } catch (FetchException e) {
            fetchCar = null;
        }

        // then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_wrong_ticket() {
        // given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        CarTicket wrongParkingTicket = new CarTicket("xxxx");

        // when
        parkingBoy.fetch(wrongParkingTicket);

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", systemOut());
    }

    @Test
    void should_fetch_null_when_fetch_given_used_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car("A001");
        CarTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        Car fetchSameCarAgain;
        try{
            fetchSameCarAgain = parkingBoy.fetch(parkingTicket);
        } catch (FetchException e){
            fetchSameCarAgain = null;
        }

        Assertions.assertNotNull(fetchCar);
        Assertions.assertNull(fetchSameCarAgain);
    }

    @Test
    void should_print_unrecognized_parking_ticket_when_fetch_given_used_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car("A001");
        CarTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);

        Throwable throwable = Assertions.assertThrows(FetchException.class,() -> parkingBoy.fetch(parkingTicket));

        //then
        Assertions.assertNotNull(fetchCar);
        Assertions.assertEquals("Unrecognized parking ticket.", throwable.getMessage());
    }

    @Test
    void should_return_null_parking_ticket_when_park_with_no_position_given_cars_and_capacity() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        int capacity = 10;
        for (int i = 1; i <= capacity; i++){
            parkingBoy.park(new Car("A00" + i));
        }

        //when
        CarTicket parkingTicket = parkingBoy.park(new Car("A0011"));

        //then
        Assertions.assertNull(parkingTicket);
    }

    @Test
    void should_print_error_msg_when_park_with_no_position_given_cars_and_capacity() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        int capacity = 10;
        for (int i = 1; i <= capacity; i++){
            parkingBoy.park(new Car("A00" + i));
        }

        //when
            parkingBoy.park(new Car("A0011"));

        //then
        Assertions.assertEquals("Not enough position.",systemOut());
    }

    @Test
    void should_return_parking_lot_one_has_9_cars_and_parking_lot_two_has_0_cars_when_park_given_9_cars_and_two_parking_lots() {
        //given
        int carsAmount = 9;
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLotList.add(parkingLotOne);
        parkingLotList.add(parkingLotTwo);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        // when
        for (int i = 0; i < carsAmount; i++) {
            parkingBoy.park(new Car("A00" + i));
        }

        //then
        Assertions.assertEquals(9,parkingLotOne.getUsedParkingPosition());
        Assertions.assertEquals(0,parkingLotTwo.getUsedParkingPosition());
    }

    @Test
    void should_return_parking_lot_one_has_10_cars_and_parking_lot_two_has_1_cars_when_park_given_11_cars_and_two_parking_lots() {
        //given
        int carsAmount = 11;
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLotList.add(parkingLotOne);
        parkingLotList.add(parkingLotTwo);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        // when
        for (int i = 0; i < carsAmount; i++) {
            parkingBoy.park(new Car("A00"+i));
        }

        //then
        Assertions.assertEquals(10,parkingLotOne.getUsedParkingPosition());
        Assertions.assertEquals(1,parkingLotTwo.getUsedParkingPosition());
    }

    @Test
    void should_print_empty_when_parking_lot_has_position_given_two_parking_lot_and_11_cars() {
        //given
        int carsAmount = 11;
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLotList.add(parkingLotOne);
        parkingLotList.add(parkingLotTwo);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        // when
        for (int i = 0; i < carsAmount; i++) {
            parkingBoy.park(new Car("A00"+i));
        }

        //then
        Assertions.assertEquals("",systemOut());
    }
}
