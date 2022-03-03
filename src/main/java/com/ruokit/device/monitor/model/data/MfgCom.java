package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "mfg_com")
public class MfgCom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mfgComId;
    private String mfgComNm;
    private Long cmpnId;

    public Long getMfgComId() {
        return mfgComId;
    }

    public void setMfgComId(Long mfgComId) {
        this.mfgComId = mfgComId;
    }

    public String getMfgComNm() {
        return mfgComNm;
    }

    public void setMfgComNm(String mfgComNm) {
        this.mfgComNm = mfgComNm;
    }

    public Long getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(Long cmpnId) {
        this.cmpnId = cmpnId;
    }
}
