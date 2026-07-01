package fi.olli.puljula.parkingmgmt.jpa.entity;

import java.time.LocalDateTime;

public class ParkingEvent {
    private String registrationNumber;
    private int spaceNumber;
    private LocalDateTime startTime;

    public ParkingEvent() {
    }

    public ParkingEvent(String registrationNumber, int spaceNumber, LocalDateTime startTime) {
        this.registrationNumber = registrationNumber;
        this.spaceNumber = spaceNumber;
        this.startTime = startTime;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
