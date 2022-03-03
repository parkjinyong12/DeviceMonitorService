package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
