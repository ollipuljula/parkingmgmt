package fi.olli.puljula.parkingmgmt.api.model;

import fi.olli.puljula.parkingmgmt.repository.model.ParkingEvent;

import java.time.LocalDateTime;

public class EnterResponse {
    private LocalDateTime startTime;
    private String registrationNumber;

    private int parkingSpaceNumber;

    public EnterResponse() {
    }

    public EnterResponse(LocalDateTime startTime, String registrationNumber, int parkingSpaceNumber) {
        this.startTime = startTime;
        this.registrationNumber = registrationNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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

    public static EnterResponse from(ParkingEvent event) {
        return new EnterResponse(event.getStartTime(), event.getRegistrationNumber(), event.getSpaceNumber());
    }
}
