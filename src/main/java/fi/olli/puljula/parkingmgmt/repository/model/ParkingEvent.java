package fi.olli.puljula.parkingmgmt.repository.model;

import java.time.LocalDateTime;

public class ParkingEvent {
    private final String registrationNumber;
    private final int spaceNumber;
    private final LocalDateTime startTime;

    public ParkingEvent(String registrationNumber, int spaceNumber, LocalDateTime startTime) {
        this.registrationNumber = registrationNumber;
        this.spaceNumber = spaceNumber;
        this.startTime = startTime;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
