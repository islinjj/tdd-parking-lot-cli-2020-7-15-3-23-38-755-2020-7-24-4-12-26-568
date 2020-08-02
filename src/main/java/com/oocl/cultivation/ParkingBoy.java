package com.oocl.cultivation;

import com.oocl.cultivation.choosestrategy.ChooseParkStrategy;
import com.oocl.cultivation.choosestrategy.ParkingBoyChooseLotStrategy;
import com.oocl.cultivation.exception.FetchException;
import com.oocl.cultivation.exception.ParkException;

import java.util.IntSummaryStatistics;
import java.util.List;

import static com.oocl.cultivation.common.Common.*;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;
    protected ChooseParkStrategy chooseParkStrategy;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
        chooseParkStrategy = new ParkingBoyChooseLotStrategy();
    }

    public CarTicket park(Car car) {
        if (car == null){
            return null;
        }
        ParkingLot parkingLot = chooseParkStrategy.chooseParkingLot(parkingLotList);
        if (parkingLot != null ){
            CarTicket carTicket = parkingLot.park(car);
            return carTicket;
        }else {
            throw new ParkException(NOT_ENOUGH_POSITION);
        }
    }

    public Car fetch(CarTicket parkingTicket) {
        if (!isProvideTicket(parkingTicket)){
            throw new FetchException(PROVIDE_TICKET);
        }
        Car car = null;
        for (ParkingLot parkingLot : parkingLotList) {
            car = parkingLot.fetch(parkingTicket);
            if (car != null){
                break;
            }
        }
        if (car == null){
            throw new FetchException(UNRECOGNIZED_PARKING_TICKET);
        }
        return car;
    }

    protected boolean isProvideTicket(CarTicket parkingTicket){
        return parkingTicket != null;
    }

    protected boolean isAllParkingLotFull(IntSummaryStatistics parkingLotUsedPositionStatistics) {
        return parkingLotUsedPositionStatistics.getMin() == 10;
    }

    protected void printErrorMsg(String s) {
        System.out.print(s);
    }
}
