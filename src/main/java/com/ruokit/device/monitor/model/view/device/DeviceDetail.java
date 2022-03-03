package com.ruokit.device.monitor.model.view.device;

public class DeviceDetail {
    // 디바이스
    private String dvcId;
    // 회사정보
    private String cmpnId;
    private String cmpnNm;
    // 데이터 유심
    private String hndsetId;
    private String hndsetNum;
    // 디바이스 모델
    private String dvcMdlId;
    private String dvcMdlNm;
    // 디바이스 제조사
    private String mfgComId;
    // 이동수단
    private String vehicleId;
    private String vehicleNum;
    // 디바이스 설치상태
    private String dvcIslSt;
    private String dvcIslStNm;
    private String dvcIslStRegDt;
    // 데이터유심 장착상태
    private String hndsetMntSt;

    public String getDvcId() {
        return dvcId;
    }

    public void setDvcId(String dvcId) {
        this.dvcId = dvcId;
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

    public String getDvcMdlId() {
        return dvcMdlId;
    }

    public void setDvcMdlId(String dvcMdlId) {
        this.dvcMdlId = dvcMdlId;
    }

    public String getDvcMdlNm() {
        return dvcMdlNm;
    }

    public void setDvcMdlNm(String dvcMdlNm) {
        this.dvcMdlNm = dvcMdlNm;
    }

    public String getMfgComId() {
        return mfgComId;
    }

    public void setMfgComId(String mfgComId) {
        this.mfgComId = mfgComId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
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

    public String getDvcIslStRegDt() {
        return dvcIslStRegDt;
    }

    public void setDvcIslStRegDt(String dvcIslStRegDt) {
        this.dvcIslStRegDt = dvcIslStRegDt;
    }

    public String getHndsetMntSt() {
        return hndsetMntSt;
    }

    public void setHndsetMntSt(String hndsetMntSt) {
        this.hndsetMntSt = hndsetMntSt;
    }
}
