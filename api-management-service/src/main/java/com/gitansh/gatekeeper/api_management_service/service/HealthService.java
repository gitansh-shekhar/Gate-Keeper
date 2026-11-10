package com.gitansh.gatekeeper.api_management_service.service;

import com.gitansh.gatekeeper.api_management_service.dto.responseDto.HealthResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final JdbcTemplate jdbcTemplate ;

    public String getHealth(){
        String status = "DOWN";
        try{
            Integer result  = jdbcTemplate.queryForObject("SELECT 1" , Integer.class);

            if(Integer.valueOf(1).equals(result)){
                status = "UP";
            }
        }
        catch (Exception e){
            System.out.println("Mysql health check failed");
        }
      return status;
    }

}
