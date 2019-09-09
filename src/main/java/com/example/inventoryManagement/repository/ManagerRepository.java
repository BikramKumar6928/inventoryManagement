package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager,String> {
}
