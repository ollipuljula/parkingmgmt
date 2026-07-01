package fi.olli.puljula.parkingmgmt.exception;

public class CarNotFoundException extends RuntimeException {
  public CarNotFoundException() {
  }

  public CarNotFoundException(String message) {
        super(message);
    }
}
