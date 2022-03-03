package com.ruokit.device.monitor.service.dvcstk;

import com.ruokit.device.monitor.model.data.Device;
import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.DvcStk;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkGroupDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DvcStkService {
    public Page<DvcStkGroupDetail> getDeviceStockGroupList(String cmpnId, Pageable pageable) throws Exception;
    public DvcStk insertDeviceStock(DvcStkRegist regist) throws Exception;
    public List<DvcStk> insertDeviceStocks(List<DvcStkRegist> regists) throws Exception;
    public DeviceModel getDeviceModelStock(String dvcMdlId) throws Exception;
    public Page<DvcStkDetail> getDeviceStockList(String cmpnId, String dvcMdlId, Pageable pageable) throws Exception;
    public void updateDeviceStock(DvcStkDetail update) throws Exception;
    public Boolean isExistDeviceStocks(DvcStkDetail dvcStkDetail) throws Exception;
}
