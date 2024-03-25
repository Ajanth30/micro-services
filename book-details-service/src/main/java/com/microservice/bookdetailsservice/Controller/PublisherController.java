package com.microservice.bookdetailsservice.Controller;

import com.microservice.bookdetailsservice.Dto.PublisherDto;
import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping("/add")
    public ResponseEntity<Response> addPublisher(@RequestBody PublisherDto publisherDto){
        return ResponseEntity.status(HttpStatus.CREATED).body( publisherService.addPublisher(publisherDto));
    }

    @DeleteMapping("/delete/publisher/{publisher_id}")
    public  ResponseEntity<Response>deletePublisher(@PathVariable("publisher_id") int id){
        return ResponseEntity.ok().body(publisherService.deletePublisher(id));
    }
}
