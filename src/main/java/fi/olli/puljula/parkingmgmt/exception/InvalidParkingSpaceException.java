package fi.olli.puljula.parkingmgmt.exception;

public class InvalidParkingSpaceException extends RuntimeException {
    public InvalidParkingSpaceException() {
    }

    public InvalidParkingSpaceException(String message) {
        super(message);
    }
}
