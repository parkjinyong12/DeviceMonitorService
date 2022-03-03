package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "cmpn")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmpnId;
    private String cmpnNm;

    public Long getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(Long cmpnId) {
        this.cmpnId = cmpnId;
    }

    public String getCmpnNm() {
        return cmpnNm;
    }

    public void setCmpnNm(String cmpnNm) {
        this.cmpnNm = cmpnNm;
    }
}
