package fi.olli.puljula.parkingmgmt.api.model;

import java.time.LocalDateTime;

public class ExitResponse {
    private LocalDateTime startTime;
    private LocalDateTime exitTime;
    private long duration;
    private long price;
    private String registrationNumber;

    private int parkingSpaceNumber;

    public ExitResponse() {
    }

    public ExitResponse(LocalDateTime startTime, LocalDateTime exitTime, long duration, long price, String registrationNumber, int parkingSpaceNumber) {
        this.startTime = startTime;
        this.exitTime = exitTime;
        this.duration = duration;
        this.price = price;
        this.registrationNumber = registrationNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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
}
