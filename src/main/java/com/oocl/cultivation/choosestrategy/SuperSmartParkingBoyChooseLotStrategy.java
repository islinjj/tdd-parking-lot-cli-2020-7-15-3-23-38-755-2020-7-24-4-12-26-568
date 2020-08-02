package com.oocl.cultivation.choosestrategy;

import com.oocl.cultivation.CarTicket;
import com.oocl.cultivation.ParkingLot;

import java.util.IntSummaryStatistics;
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
                double calculateRate = 1.0 - ((parkingLot.getCapacity()-parkingLot.getUsedParkingPosition()) * 1.0 / parkingLot.getCapacity());
                if (calculateRate > maxCapacityRate) {
                    bestParkingLot = parkingLot;
                    maxCapacityRate = calculateRate;
                }
            }
        }
        return bestParkingLot;
    }
}
