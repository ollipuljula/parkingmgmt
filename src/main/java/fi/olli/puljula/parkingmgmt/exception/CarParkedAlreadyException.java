package fi.olli.puljula.parkingmgmt.exception;

public class CarParkedAlreadyException extends RuntimeException {
    public CarParkedAlreadyException() {
    }

    public CarParkedAlreadyException(String message) {
        super(message);
    }
}
