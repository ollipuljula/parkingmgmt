package fi.olli.puljula.parkingmgmt.api.model;

import fi.olli.puljula.parkingmgmt.repository.model.LotStatus;

public class LotResponse {
    private int spaceCount;

    private int freeSpaceCount;

    private int occupiedSpaceCount;

    private boolean full;

    public LotResponse() {
    }

    public LotResponse(int spaceCount, int freeSpaceCount, int occupiedSpaceCount, boolean full) {
        this.spaceCount = spaceCount;
        this.freeSpaceCount = freeSpaceCount;
        this.occupiedSpaceCount = occupiedSpaceCount;
        this.full = full;
    }

    public int getSpaceCount() {
        return spaceCount;
    }

    public void setSpaceCount(int spaceCount) {
        this.spaceCount = spaceCount;
    }

    public int getFreeSpaceCount() {
        return freeSpaceCount;
    }

    public void setFreeSpaceCount(int freeSpaceCount) {
        this.freeSpaceCount = freeSpaceCount;
    }

    public int getOccupiedSpaceCount() {
        return occupiedSpaceCount;
    }

    public void setOccupiedSpaceCount(int occupiedSpaceCount) {
        this.occupiedSpaceCount = occupiedSpaceCount;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public static LotResponse from(LotStatus lotStatus) {
        return new LotResponse(lotStatus.getSpaceCount(),
                lotStatus.getSpaceCount() - lotStatus.getOccupiedSpaceCount(),
                lotStatus.getOccupiedSpaceCount(), lotStatus.getOccupiedSpaceCount() >= lotStatus.getSpaceCount());
    }
}
