package com.gitansh.gatekeeper.api_management_service.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

     private String token;
     private String tokenType;
     private long expirationSeconds;
     private String role;
     private Long organizationId;
}
