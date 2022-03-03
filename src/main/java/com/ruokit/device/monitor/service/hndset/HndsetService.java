package com.ruokit.device.monitor.service.hndset;

import com.ruokit.device.monitor.model.view.hndset.HndsetDetail;
import com.ruokit.device.monitor.model.view.hndset.HndsetOpenDetail;
import com.ruokit.device.monitor.model.view.hndset.HndsetOpenRegist;
import com.ruokit.device.monitor.model.view.hndset.HndsetRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HndsetService {
    public Page<HndsetDetail> getHndsetList(String cmpnId, Pageable pageable) throws Exception;
    public Page<HndsetDetail> getHndsetList(String cmpnId, String hndsetNum, Pageable pageable) throws Exception;
    public Page<HndsetDetail> getUsableHndsetList(String cmpnId, Pageable pageable) throws Exception;
    public Page<HndsetDetail> getUsableHndsetList(String cmpnId, String hndsetNum, Pageable pageable) throws Exception;
    public Page<HndsetOpenDetail> getHndsetOpenList(String hndsetId, Pageable pageable) throws Exception;
    public void insertHndsetOpen(HndsetOpenRegist regist) throws Exception;
    public void insertHndset(HndsetRegist regist) throws Exception;
    public void updateHndset(HndsetDetail update) throws Exception;
    public HndsetDetail getHndsetDetail(String hndsetId) throws Exception;
    public void deleteHndset(String hndsetId) throws Exception;
}
