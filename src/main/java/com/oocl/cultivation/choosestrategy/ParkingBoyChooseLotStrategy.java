package com.oocl.cultivation.choosestrategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;

import static com.oocl.cultivation.common.Common.CAPACITY;
import static com.oocl.cultivation.common.Common.getLotStatistics;

public class ParkingBoyChooseLotStrategy implements ChooseParkStrategy {

  @Override
  public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) {
    if (getLotStatistics(parkingLots).getMin() != CAPACITY) {
      for (ParkingLot parkingLot : parkingLots) {
        if (parkingLot.getCapacity() != 0) {
          return parkingLot;
        }
      }
    }
    return null;
  }

}
