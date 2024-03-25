package com.microservice.bookdetailsservice.ServiceImpl;

import com.microservice.bookdetailsservice.Dto.PublisherDto;
import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Entity.Publisher;
import com.microservice.bookdetailsservice.Repo.PublisherRepo;
import com.microservice.bookdetailsservice.Service.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepo publisherRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response addPublisher(PublisherDto publisherDto) {
        Publisher publisher=mapper.map(publisherDto,Publisher.class);
        publisherRepo.save(publisher);

        return Response.success(publisherDto) ;
    }

    @Override
    public Response deletePublisher(int id) {
        publisherRepo.deleteById(id);
        return Response.success("deleted");
    }
}
