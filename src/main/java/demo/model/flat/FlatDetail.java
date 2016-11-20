package demo.model.flat;


public class FlatDetail {

    private boolean locked;
    private boolean selfOccupied;
    private ParkingType parkingType;
    private FlatType flatType;

    public boolean isSelfOccupied() {
        return selfOccupied;
    }

    public FlatDetail setSelfOccupied(boolean selfOccupied) {
        this.selfOccupied = selfOccupied;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public FlatDetail setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public ParkingType getParkingType() {
        return parkingType;
    }

    public FlatDetail setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
        return this;
    }

    public FlatType getFlatType() {
        return flatType;
    }

    public FlatDetail setFlatType(FlatType flatType) {
        this.flatType = flatType;
        return this;
    }

}
