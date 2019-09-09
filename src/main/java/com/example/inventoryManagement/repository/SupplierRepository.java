package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier,String> {
}
