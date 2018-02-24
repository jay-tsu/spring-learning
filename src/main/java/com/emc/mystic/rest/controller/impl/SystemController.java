package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.service.SystemService;
import com.emc.mystic.util.webutil.RequestParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController implements com.emc.mystic.rest.controller.System {
    private static final Logger logger = LogManager.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    @Override
    public String getSystemHealth(final RequestParameters params) {
        return systemService.getSystemHealth();
    }

}
