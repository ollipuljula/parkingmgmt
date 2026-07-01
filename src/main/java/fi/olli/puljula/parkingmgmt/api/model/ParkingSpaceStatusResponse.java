package fi.olli.puljula.parkingmgmt.api.model;

import fi.olli.puljula.parkingmgmt.repository.model.ParkingEvent;

public class ParkingSpaceStatusResponse {
    private int spaceNumber;
    private boolean isAvailable;
    private String registrationNumber;

    public ParkingSpaceStatusResponse(int spaceNumber, boolean isAvailable, String registrationNumber) {
        this.spaceNumber = spaceNumber;
        this.isAvailable = isAvailable;
        this.registrationNumber = registrationNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public static ParkingSpaceStatusResponse from(ParkingEvent event) {
        return new ParkingSpaceStatusResponse(event.getSpaceNumber(), false, event.getRegistrationNumber());
    }
}
