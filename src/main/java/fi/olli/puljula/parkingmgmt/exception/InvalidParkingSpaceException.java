package fi.olli.puljula.parkingmgmt.exception;

/***
 * Kuvaa tilannetta, jossa annetaan parkkipaikan numero, jota ei ole olemassa
 */
public class InvalidParkingSpaceException extends RuntimeException {
    public InvalidParkingSpaceException() {
    }

    public InvalidParkingSpaceException(String message) {
        super(message);
    }
}
