package com.oocl.cultivation.choosestrategy;

import com.oocl.cultivation.CarTicket;
import com.oocl.cultivation.ParkingLot;

import java.util.IntSummaryStatistics;
import java.util.List;

import static com.oocl.cultivation.common.Common.CAPACITY;
import static com.oocl.cultivation.common.Common.getLotStatistics;

public class SmartParkingBoyChooseLotStrategy implements ChooseParkStrategy {
    @Override
    public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) {
        if (getLotStatistics(parkingLots).getMin() != CAPACITY) {
            IntSummaryStatistics intSummaryStatistics = getLotStatistics(parkingLots);
            for (ParkingLot parkingLot : parkingLots) {
                if (isParkingLotHasMinUsedPosition(intSummaryStatistics,parkingLot)){
                    return parkingLot;
                }
            }
        }
        return null;
    }

    private boolean isParkingLotHasMinUsedPosition(
            IntSummaryStatistics intSummaryStatistics, ParkingLot parkingLot) {
        return parkingLot.getUsedParkingPosition() == intSummaryStatistics.getMin();
    }
}
