package com.emc.mystic.repository;

import com.emc.mystic.model.JobBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobDao extends JpaRepository<JobBean, UUID> {
}
