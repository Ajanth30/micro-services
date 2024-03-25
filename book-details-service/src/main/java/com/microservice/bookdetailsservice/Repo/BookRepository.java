package com.microservice.bookdetailsservice.Repo;

import com.microservice.bookdetailsservice.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}
