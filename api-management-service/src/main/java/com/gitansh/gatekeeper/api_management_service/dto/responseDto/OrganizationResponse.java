package com.gitansh.gatekeeper.api_management_service.dto.responseDto;

import com.gitansh.gatekeeper.api_management_service.entity.Organization;
import com.gitansh.gatekeeper.api_management_service.enums.OrganizationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class OrganizationResponse {


    private String name;
    private String slug;
    @Enumerated(EnumType.STRING)
    private OrganizationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static OrganizationResponse from(Organization organization){
        return new OrganizationResponse(organization.getName() ,
                          organization.getSlug(),
                           organization.getStatus() ,
                           organization.getCreatedAt() ,
                           organization.getUpdatedAt() , organization.getDeletedAt());
    }
}
