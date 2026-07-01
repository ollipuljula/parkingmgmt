package fi.olli.puljula.parkingmgmt.exception;

/***
 * Kuvaa tilannetta, jossa parkkipaikka ei ole vapaana
 */
public class ParkingSpaceNotAvailableException extends RuntimeException {
    public ParkingSpaceNotAvailableException() {
    }

    public ParkingSpaceNotAvailableException(String message) {
        super(message);
    }
}
