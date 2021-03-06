package com.ruokit.device.monitor.service;

import com.ruokit.device.monitor.model.data.Test;
import com.ruokit.device.monitor.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getTestList() {
        return testRepository.findAll();
    }
    public Long  countTests() {
        return testRepository.count();
    }
}
