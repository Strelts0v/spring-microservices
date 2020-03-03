package com.straltsou.limitsservice.controller;

import com.straltsou.limitsservice.bean.LimitConfig;
import com.straltsou.limitsservice.config.MicroserviceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private MicroserviceConfig config;

    @GetMapping(path = "/limits")
    public LimitConfig getLimitConfiguration() {
        return new LimitConfig(config.getMaximum(), config.getMinimum());
    }
}
