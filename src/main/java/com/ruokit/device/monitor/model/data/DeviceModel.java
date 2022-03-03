package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "device_model")
public class DeviceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvcMdlId;

    private String dvcMdlNm;
    private Long mfgComId;

    public Long getDvcMdlId() {
        return dvcMdlId;
    }

    public void setDvcMdlId(Long dvcMdlId) {
        this.dvcMdlId = dvcMdlId;
    }

    public String getDvcMdlNm() {
        return dvcMdlNm;
    }

    public void setDvcMdlNm(String dvcMdlNm) {
        this.dvcMdlNm = dvcMdlNm;
    }

    public Long getMfgComId() {
        return mfgComId;
    }

    public void setMfgComId(Long mfgComId) {
        this.mfgComId = mfgComId;
    }
}
