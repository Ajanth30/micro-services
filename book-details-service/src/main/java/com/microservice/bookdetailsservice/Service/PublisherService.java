package com.microservice.bookdetailsservice.Service;

import com.microservice.bookdetailsservice.Dto.PublisherDto;
import com.microservice.bookdetailsservice.Dto.Response;

public interface PublisherService {

    Response addPublisher(PublisherDto publisherDto);

    Response deletePublisher(int id);
}
