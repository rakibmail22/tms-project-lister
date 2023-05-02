package com.tms.project.repository;

import com.tms.project.repository.entity.TmsUserConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TmsUserConfigRepository extends JpaRepository<TmsUserConfig, Integer> {

	Optional<TmsUserConfig> findByUuid(UUID uuid);

	Optional<TmsUserConfig> findByUsername(String username);
}
