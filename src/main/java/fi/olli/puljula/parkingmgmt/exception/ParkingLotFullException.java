package fi.olli.puljula.parkingmgmt.exception;
/*
    Kuvaa tilannetta, jossa parkkihalli on täynnä
 */
public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException() {
    }

    public ParkingLotFullException(String message) {
        super(message);
    }
}
