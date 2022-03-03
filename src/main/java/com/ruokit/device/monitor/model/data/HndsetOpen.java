package com.ruokit.device.monitor.model.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hndset_open")
public class HndsetOpen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hndsetOpenId;

    private Long hndsetId;
    private String hndsetCarrierCd;
    private String hndsetCarrierNm;
    private String hndsetOpenSt;
    private String hndsetOpenStNm;

    @Column(insertable = false)
    private LocalDateTime regDt;

    public Long getHndsetOpenId() {
        return hndsetOpenId;
    }

    public void setHndsetOpenId(Long hndsetOpenId) {
        this.hndsetOpenId = hndsetOpenId;
    }

    public Long getHndsetId() {
        return hndsetId;
    }

    public void setHndsetId(Long hndsetId) {
        this.hndsetId = hndsetId;
    }

    public String getHndsetCarrierCd() {
        return hndsetCarrierCd;
    }

    public void setHndsetCarrierCd(String hndsetCarrierCd) {
        this.hndsetCarrierCd = hndsetCarrierCd;
    }

    public String getHndsetCarrierNm() {
        return hndsetCarrierNm;
    }

    public void setHndsetCarrierNm(String hndsetCarrierNm) {
        this.hndsetCarrierNm = hndsetCarrierNm;
    }

    public String getHndsetOpenSt() {
        return hndsetOpenSt;
    }

    public void setHndsetOpenSt(String hndsetOpenSt) {
        this.hndsetOpenSt = hndsetOpenSt;
    }

    public String getHndsetOpenStNm() {
        return hndsetOpenStNm;
    }

    public void setHndsetOpenStNm(String hndsetOpenStNm) {
        this.hndsetOpenStNm = hndsetOpenStNm;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }
}
