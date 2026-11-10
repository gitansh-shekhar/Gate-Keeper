package com.gitansh.gatekeeper.api_management_service.service;


import com.gitansh.gatekeeper.api_management_service.dto.requestDto.LoginRequest;
import com.gitansh.gatekeeper.api_management_service.dto.responseDto.LoginResponse;
import com.gitansh.gatekeeper.api_management_service.entity.ManagementUser;
import com.gitansh.gatekeeper.api_management_service.enums.OrganizationStatus;
import com.gitansh.gatekeeper.api_management_service.exception.ApiException;
import com.gitansh.gatekeeper.api_management_service.repository.ManagementUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthenticationService {

    private final ManagementUserRepository managementUserRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final Long expirationSeconds;

    AuthenticationService(ManagementUserRepository managementUserRepository ,
                          JwtEncoder jwtEncoder , PasswordEncoder passwordEncoder ,
                          @Value("${security.jwt.expiration-seconds}") Long expirationSeconds){
        this.managementUserRepository = managementUserRepository;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.expirationSeconds = expirationSeconds;
    }
    @Transactional()
    public LoginResponse login(LoginRequest loginRequest){
        ManagementUser user = managementUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(()-> new ApiException(HttpStatus.FORBIDDEN , "Wrong credentiail"));
        if(!user.isEnabled() || !passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())){
            throw InvalidCredentials();
        }

        if(user.getOrganization() !=null && user.getOrganization().getStatus() != OrganizationStatus.ACTIVE){
            throw  new ApiException(HttpStatus.FORBIDDEN , "Organization is not active");
        }

        Instant issuedAt = Instant.now() ;
        String role = user.getRole().getName();
        Long organizationId = user.getOrganization() == null ? null : user.getOrganization().getId();


        JwtClaimsSet.Builder claims = JwtClaimsSet.builder()
                .issuer("gateKeeper-api-management")
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(expirationSeconds))
                .subject(user.getUsername())
                .claim("role", role);

// Add organizationId only for organization-specific users
        if (organizationId != null) {
            claims.claim("organizationId", organizationId);
        }

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        String token = jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims.build())
        ).getTokenValue();


        return new LoginResponse(token , "Bearer" , expirationSeconds , role , organizationId);



    }
     private RuntimeException InvalidCredentials(){
        return new RuntimeException("Invalid Credentials");
     }
}
