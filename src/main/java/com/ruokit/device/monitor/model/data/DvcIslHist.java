package com.ruokit.device.monitor.model.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "dvc_isl_hist")
public class DvcIslHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvcIslHistId;
    private Long dvcId;
    private String dvcIslSt;
    private String dvcIslStNm;
    private String regUserId;
    private Long regCmpnId;
    private Long islReqCmpnId;

    @Column(insertable = false)
    private LocalDateTime regDt;

    public Long getDvcIslHistId() {
        return dvcIslHistId;
    }

    public void setDvcIslHistId(Long dvcIslHistId) {
        this.dvcIslHistId = dvcIslHistId;
    }

    public Long getDvcId() {
        return dvcId;
    }

    public void setDvcId(Long dvcId) {
        this.dvcId = dvcId;
    }

    public String getDvcIslSt() {
        return dvcIslSt;
    }

    public void setDvcIslSt(String dvcIslSt) {
        this.dvcIslSt = dvcIslSt;
    }

    public String getDvcIslStNm() {
        return dvcIslStNm;
    }

    public void setDvcIslStNm(String dvcIslStNm) {
        this.dvcIslStNm = dvcIslStNm;
    }

    public String getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(String regUserId) {
        this.regUserId = regUserId;
    }

    public Long getRegCmpnId() {
        return regCmpnId;
    }

    public void setRegCmpnId(Long regCmpnId) {
        this.regCmpnId = regCmpnId;
    }

    public Long getIslReqCmpnId() {
        return islReqCmpnId;
    }

    public void setIslReqCmpnId(Long islReqCmpnId) {
        this.islReqCmpnId = islReqCmpnId;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }
}
