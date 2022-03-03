package com.ruokit.device.monitor.model.service.device;

public class deviceModel {
    private Long dvcId;
    private String companyNm;
    private String hndsetId;
    private String dvcMdlId;

    public Long getDvcId() {
        return dvcId;
    }

    public void setDvcId(Long dvcId) {
        this.dvcId = dvcId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getHndsetId() {
        return hndsetId;
    }

    public void setHndsetId(String hndsetId) {
        this.hndsetId = hndsetId;
    }

    public String getDvcMdlId() {
        return dvcMdlId;
    }

    public void setDvcMdlId(String dvcMdlId) {
        this.dvcMdlId = dvcMdlId;
    }
}
