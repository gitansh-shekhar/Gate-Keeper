package com.gitansh.gatekeeper.api_management_service.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter@AllArgsConstructor
public class OrganizationRequest {
    @NotBlank
    @Size(max = 150)
    private String name;
    @NotBlank
    @Size(max = 150)
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$" ,
                        message = "slug must contain lowercase letters, numbers and single hyphen")
    private String slug;
}
