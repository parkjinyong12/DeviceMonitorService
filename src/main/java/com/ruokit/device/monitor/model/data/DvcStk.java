package com.ruokit.device.monitor.model.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dvc_stk")
public class DvcStk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvcStkId;

    private Long dvcMdlId;
    private String dvcSn;
    private Long cmpnId;

    @Column(insertable = false)
    private LocalDateTime regDt;

    public Long getDvcStkId() {
        return dvcStkId;
    }

    public void setDvcStkId(Long dvcStkId) {
        this.dvcStkId = dvcStkId;
    }

    public Long getDvcMdlId() {
        return dvcMdlId;
    }

    public void setDvcMdlId(Long dvcMdlId) {
        this.dvcMdlId = dvcMdlId;
    }

    public String getDvcSn() {
        return dvcSn;
    }

    public void setDvcSn(String dvcSn) {
        this.dvcSn = dvcSn;
    }

    public Long getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(Long cmpnId) {
        this.cmpnId = cmpnId;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }
}
