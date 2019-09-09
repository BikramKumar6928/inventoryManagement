package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDetails,String> {
}
