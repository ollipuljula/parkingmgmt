package fi.olli.puljula.parkingmgmt.repository.model;

public class LotStatus {
    private int spaceCount;
    private int occupiedSpaceCount;

    public LotStatus(int spaceCount, int occupiedSpaceCount) {
        this.spaceCount = spaceCount;
        this.occupiedSpaceCount = occupiedSpaceCount;
    }

    public int getSpaceCount() {
        return spaceCount;
    }

    public int getOccupiedSpaceCount() {
        return occupiedSpaceCount;
    }
}
