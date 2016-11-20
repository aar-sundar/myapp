package demo.model.user;


import demo.model.flat.FlatDetail;
import demo.model.vehicle.VehicleDetail;

public class UserDetail {

    private String address;
    private String mobile;
    private String alternateMobile;
    private String landLine;
    private FlatDetail flatDetail;
    private VehicleDetail vehicleDetail;
    private UserType userType;

    public String getAddress() {
        return address;
    }

    public UserDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserDetail setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAlternateMobile() {
        return alternateMobile;
    }

    public UserDetail setAlternateMobile(String alternateMobile) {
        this.alternateMobile = alternateMobile;
        return this;
    }

    public String getLandLine() {
        return landLine;
    }

    public UserDetail setLandLine(String landLine) {
        this.landLine = landLine;
        return this;
    }

    public FlatDetail getFlatDetail() {
        return flatDetail;
    }

    public UserDetail setFlatDetail(FlatDetail flatDetail) {
        this.flatDetail = flatDetail;
        return this;
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public UserDetail setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public UserDetail setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

}
