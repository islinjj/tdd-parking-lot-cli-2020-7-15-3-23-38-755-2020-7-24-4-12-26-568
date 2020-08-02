package com.oocl.cultivation;

import com.oocl.cultivation.choosestrategy.ChooseParkStrategy;
import com.oocl.cultivation.choosestrategy.ParkingBoyChooseLotStrategy;
import com.oocl.cultivation.exception.FetchException;
import com.oocl.cultivation.exception.ParkException;

import java.util.List;

import static com.oocl.cultivation.common.Common.*;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;
    protected ChooseParkStrategy chooseParkStrategy;
    protected int id;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
        chooseParkStrategy = new ParkingBoyChooseLotStrategy();
    }

    public ParkingBoy(int id) {
        this.id = id;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList, int id) {
        this.parkingLotList = parkingLotList;
        this.id = id;
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
        }
        throw new ParkException(NOT_ENOUGH_POSITION);
    }

    public Car fetch(CarTicket parkingTicket) {
        if (!isProvideTicket(parkingTicket)){
            throw new FetchException(PROVIDE_TICKET);
        }
        for (ParkingLot parkingLot : parkingLotList) {
            Car car = parkingLot.fetch(parkingTicket);
            if (car != null){
                return car;
            }
        }
        throw new FetchException(UNRECOGNIZED_PARKING_TICKET);
    }

    protected boolean isProvideTicket(CarTicket parkingTicket){
        return parkingTicket != null;
    }
}
