package com.gitansh.gatekeeper.api_management_service.repository;

import com.gitansh.gatekeeper.api_management_service.entity.Organization;
import com.gitansh.gatekeeper.api_management_service.enums.OrganizationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization , Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsBySlugIgnoreCase(String slug);

    List<Organization> findAllByStatusNot(OrganizationStatus status);

    Optional<Organization> findByIdAndStatusNot(Long id, OrganizationStatus status);
}
