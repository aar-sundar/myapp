package demo.model.vehicle;


public class VehicleDetail {

    private String vehicleNumber;
    private VehicleType vehicleType;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleDetail setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleDetail setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

}
