package com.microservice.apigatewayservice.service;

import com.microservice.apigatewayservice.dto.Credentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

//@FeignClient(value = "AUTH-SERVICE",url = "http://localhost:8088/")
//
//public interface AuthClientService {
//    @PostMapping(value = "/login")
//    Map<String,String>login(@RequestBody Credentials credentials);
//
//
//}
