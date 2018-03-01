package com.emc.mystic.repository;

import com.emc.mystic.model.LogBundleBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogBundleDao extends JpaRepository<LogBundleBean, UUID> {
}
