package com.microservice.authserver.controller;

import com.microservice.authserver.model.Credentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AuthController {

    @Value("${jwt.secret}")
    public String secret;

    @PostMapping("/login")
    public Map<String,String>login(@RequestBody Credentials credential){
        Map<String,String> response=new HashMap<>();
        if(credential.getUsername().equals("Ajanth30") && credential.getPassword().equals("Ajanth@330")){
            String token= Jwts.builder()
                    .claim("id","1")
                    .claim("username",credential.getUsername())
                    .claim("role","ADMIN")
                    .setSubject("Test Token")
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .signWith(SignatureAlgorithm.HS256,secret).compact();
            response.put("token",token);
            response.put("massage","Authentication successful");

        }else {
            response.put("token",null);
            response.put("message","invalid credentials");
        }
        return response;
    }
}
