package com.ruokit.device.monitor.model.view.hndset;

public class HndsetDetail {
    private String hndsetId;
    private String hndsetNum;
    private String cmpnId;
    private String cmpnNm;

    // 개통이력은 최신 1건
    private String hndsetOpenSt;
    private String hndsetOpenStNm;
    private String hndsetCarrierCd;
    private String hndsetCarrierNm;

    public String getHndsetId() {
        return hndsetId;
    }

    public void setHndsetId(String hndsetId) {
        this.hndsetId = hndsetId;
    }

    public String getHndsetNum() {
        return hndsetNum;
    }

    public void setHndsetNum(String hndsetNum) {
        this.hndsetNum = hndsetNum;
    }

    public String getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }

    public String getCmpnNm() {
        return cmpnNm;
    }

    public void setCmpnNm(String cmpnNm) {
        this.cmpnNm = cmpnNm;
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
}
