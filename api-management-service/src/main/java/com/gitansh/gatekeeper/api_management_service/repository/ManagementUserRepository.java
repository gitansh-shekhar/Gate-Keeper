package com.gitansh.gatekeeper.api_management_service.repository;

import com.gitansh.gatekeeper.api_management_service.entity.ManagementUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ManagementUserRepository extends JpaRepository<ManagementUser ,Long> {

    Optional< ManagementUser> findByUsername(String userName);
    Optional<ManagementUser> findByOrganizationId(Long organizationId);

    boolean existsByUsername(String username);
}
