package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "hndset")
public class Hndset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hndsetId;
    private String hndsetNum;
    private Long cmpnId;
    private Long dvcId;

    public Long getHndsetId() {
        return hndsetId;
    }

    public void setHndsetId(Long hndsetId) {
        this.hndsetId = hndsetId;
    }

    public String getHndsetNum() {
        return hndsetNum;
    }

    public void setHndsetNum(String hndsetNum) {
        this.hndsetNum = hndsetNum;
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
