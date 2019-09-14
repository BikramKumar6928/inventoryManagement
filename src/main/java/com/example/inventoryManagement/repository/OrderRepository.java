package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDetails,String> {
    public List<OrderDetails> findAllByOwner(String ownerId);
}
