package com.emc.mystic.rest.controller;

import com.emc.mystic.util.webutil.RequestParameters;
import org.springframework.web.bind.annotation.*;

@RestController
public interface SystemInterface {
    @RequestMapping(value = "/system-health", method = RequestMethod.GET, produces = "text/plain" )
    String getSystemHealth(final RequestParameters params);
}
