package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByVehicleId(Long vehicleId);
    Vehicle findByDvcId(Long dvcId);
    Page<Vehicle> findByCmpnIdAndDvcId(Long cmpnId, String dvcId, Pageable pageable);
    Page<Vehicle> findByCmpnIdAndDvcIdAndVehicleNumContaining(Long cmpnId, String dvcId, String vehicleNum, Pageable pageable);
    Page<Vehicle> findByCmpnId(Long cmpnId, Pageable pageable);
    Page<Vehicle> findByCmpnIdAndVehicleNumContaining(Long cmpnId, String vehicleNum, Pageable pageable);
}
