package com.oocl.cultivation.choosestrategy;

import com.oocl.cultivation.ParkingLot;
import java.util.List;

import static com.oocl.cultivation.common.Common.CAPACITY;
import static com.oocl.cultivation.common.Common.getLotStatistics;

public class SuperSmartParkingBoyChooseLotStrategy implements ChooseParkStrategy {
    @Override
    public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot bestParkingLot = null;
        if (getLotStatistics(parkingLots).getMin() != CAPACITY) {
            double maxCapacityRate = -1;
            for (ParkingLot parkingLot : parkingLots) {
                double rate = CAPACITY - parkingLot.getUsedParkingPosition() / parkingLot.getCapacity();
                if (rate > maxCapacityRate) {
                    bestParkingLot = parkingLot;
                    maxCapacityRate = rate;
                }
            }
        }
        return bestParkingLot;
    }
}
