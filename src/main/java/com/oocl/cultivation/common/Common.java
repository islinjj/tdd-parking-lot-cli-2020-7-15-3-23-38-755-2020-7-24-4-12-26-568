package com.oocl.cultivation.common;

import com.oocl.cultivation.ParkingLot;

import java.util.IntSummaryStatistics;
import java.util.List;

public class Common {
    public static final int CAPACITY = 10;

    public static final String NOT_ENOUGH_POSITION =  "Not enough position.";

    public static final String PROVIDE_TICKET = "Please provide your parking ticket.";

    public static final String UNRECOGNIZED_PARKING_TICKET =  "Unrecognized parking ticket.";


    public static IntSummaryStatistics getLotStatistics(List<ParkingLot> parkingLots) {
        return parkingLots.stream().mapToInt((x) -> x.getUsedParkingPosition()).summaryStatistics();
    }
}
