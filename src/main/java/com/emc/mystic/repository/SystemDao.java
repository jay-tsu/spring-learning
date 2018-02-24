package com.emc.mystic.repository;

import com.emc.mystic.model.SystemBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemDao extends JpaRepository<SystemBean, Long> {
}
