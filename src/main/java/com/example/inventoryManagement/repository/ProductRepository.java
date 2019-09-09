package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,String> {
}
