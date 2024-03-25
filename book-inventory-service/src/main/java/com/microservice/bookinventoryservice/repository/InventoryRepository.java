package com.microservice.bookinventoryservice.repository;

import com.microservice.bookinventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    Inventory findByBookId(int bookId);
}
