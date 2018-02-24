package com.emc.mystic.service.impl;

import com.emc.mystic.model.SystemBean;
import com.emc.mystic.repository.SystemDao;
import com.emc.mystic.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDao systemDao;

    @Override
    public String getSystemHealth() {
        SystemBean systemBean = systemDao.findAll().get(0);
        return systemBean.getHealth();
    }
}