package com.oocl.cultivation.choosestrategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;

public interface ChooseParkStrategy {
    ParkingLot chooseParkingLot(List<ParkingLot> parkingLots);
}
