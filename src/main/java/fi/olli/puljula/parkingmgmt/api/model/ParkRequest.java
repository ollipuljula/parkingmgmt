package fi.olli.puljula.parkingmgmt.api.model;

public class ParkRequest {

    private String registrationNumber;

    private int parkingSpaceNumber;

    public ParkRequest() {
    }

    public ParkRequest(String registrationNumber, int parkingSpaceNumber) {
        this.registrationNumber = registrationNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(int parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }
}
