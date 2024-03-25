package com.microservice.bookdetailsservice.Repo;

import com.microservice.bookdetailsservice.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher,Integer> {

}
