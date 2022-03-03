package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vehicleNum;
    private String vehicleTp;
    private Long cmpnId;
    private Long dvcId;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getVehicleTp() {
        return vehicleTp;
    }

    public void setVehicleTp(String vehicleTp) {
        this.vehicleTp = vehicleTp;
    }

    public Long getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(Long cmpnId) {
        this.cmpnId = cmpnId;
    }

    public Long getDvcId() {
        return dvcId;
    }

    public void setDvcId(Long dvcId) {
        this.dvcId = dvcId;
    }
}
