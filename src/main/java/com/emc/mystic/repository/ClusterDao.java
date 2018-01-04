package com.emc.mystic.repository;

import com.emc.mystic.model.ClusterBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClusterDao extends JpaRepository<ClusterBean, Long> {
}
