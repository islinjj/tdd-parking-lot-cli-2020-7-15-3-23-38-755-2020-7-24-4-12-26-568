package com.oocl.cultivation.test;

import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParkingManagerTest {

    ParkingManager parkingManager;

    @Test
    void should_return_1_parking_boy_when_add_1_parking_boy_given_1_parking_boy() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        parkingManager = new ParkingManager();

        //when
        parkingManager.addParkingBoy(parkingBoy);

        //then
        assertEquals(1,parkingManager.getParkingBoys().size());
    }
}
