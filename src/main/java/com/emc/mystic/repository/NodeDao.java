package com.emc.mystic.repository;

import com.emc.mystic.model.NodeBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeDao extends JpaRepository<NodeBean, Long> {
}
