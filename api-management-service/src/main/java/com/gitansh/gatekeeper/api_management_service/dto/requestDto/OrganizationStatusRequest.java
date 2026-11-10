package com.gitansh.gatekeeper.api_management_service.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrganizationStatusRequest {
    @NotNull
    private String status;
}
