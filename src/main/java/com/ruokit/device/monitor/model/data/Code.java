package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "cd")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdId;
    private String upperCd;
    private String cd;
    private String cdNm;
    private String cdDc;

    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public String getUpperCd() {
        return upperCd;
    }

    public void setUpperCd(String upperCd) {
        this.upperCd = upperCd;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public String getCdDc() {
        return cdDc;
    }

    public void setCdDc(String cdDc) {
        this.cdDc = cdDc;
    }
}
