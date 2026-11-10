package com.gitansh.gatekeeper.api_management_service.controller;


import com.gitansh.gatekeeper.api_management_service.dto.requestDto.LoginRequest;
import com.gitansh.gatekeeper.api_management_service.dto.responseDto.LoginResponse;
import com.gitansh.gatekeeper.api_management_service.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
       private final AuthenticationService authenticationService ;

       AuthenticationController (AuthenticationService authenticationService){
           this.authenticationService = authenticationService;
       }

       @PostMapping("/login")
       public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
             LoginResponse loginResponse = authenticationService.login(loginRequest);
             return ResponseEntity.ok(loginResponse);
       }




}
