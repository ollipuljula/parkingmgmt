package fi.olli.puljula.parkingmgmt.exception;

/*
    Kuvaa tilannetta, jossa parkkipaikka ei ole vapaana
 */
public class ParkingSpaceNotAvailable extends RuntimeException {
    public ParkingSpaceNotAvailable() {
    }

    public ParkingSpaceNotAvailable(String message) {
        super(message);
    }
}
