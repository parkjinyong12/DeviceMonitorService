package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvcId;
    private Long cmpnId;
    private Long dvcMdlId;
    private String lstDvcIslSt;
    @Column(insertable = false)
    private String hndsetMntSt;

    public Long getDvcId() {
        return dvcId;
    }

    public void setDvcId(Long dvcId) {
        this.dvcId = dvcId;
    }

    public Long getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(Long cmpnId) {
        this.cmpnId = cmpnId;
    }

    public Long getDvcMdlId() {
        return dvcMdlId;
    }

    public void setDvcMdlId(Long dvcMdlId) {
        this.dvcMdlId = dvcMdlId;
    }

    public String getLstDvcIslSt() {
        return lstDvcIslSt;
    }

    public void setLstDvcIslSt(String lstDvcIslSt) {
        this.lstDvcIslSt = lstDvcIslSt;
    }

    public String getHndsetMntSt() {
        return hndsetMntSt;
    }

    public void setHndsetMntSt(String hndsetMntSt) {
        this.hndsetMntSt = hndsetMntSt;
    }
}
