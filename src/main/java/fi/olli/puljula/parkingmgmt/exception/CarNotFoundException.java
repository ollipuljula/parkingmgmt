package fi.olli.puljula.parkingmgmt.exception;

/***
 * Kuvaa tilannetta, jossa yritetään lopettaa pysäköintiä rekisterinumerolla, jota ei löydy hallista
 */
public class CarNotFoundException extends RuntimeException {
  public CarNotFoundException() {
  }

  public CarNotFoundException(String message) {
        super(message);
    }
}
