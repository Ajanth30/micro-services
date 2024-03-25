//package com.microservice.apigatewayservice.utils;
//
//import com.microservice.apigatewayservice.dto.Credentials;
//import com.microservice.apigatewayservice.service.AuthClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class AuthUtil  {
//
//    @Autowired
//    private AuthClientService authClientService;
//
//    public void login(Credentials credentials){
//        try{
//            authClientService.login(credentials);
//        }catch (Exception ex){
//            throw new RuntimeException("invalid credentials");
//        }
//    }
//
//}
