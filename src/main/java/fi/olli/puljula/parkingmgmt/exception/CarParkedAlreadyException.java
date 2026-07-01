package fi.olli.puljula.parkingmgmt.exception;

/***
 * Kuvaa tilannetta, jossa yritetään parkkeerata autoa, joka on jo parkkeerattu
 */
public class CarParkedAlreadyException extends RuntimeException {
    public CarParkedAlreadyException() {
    }

    public CarParkedAlreadyException(String message) {
        super(message);
    }
}
