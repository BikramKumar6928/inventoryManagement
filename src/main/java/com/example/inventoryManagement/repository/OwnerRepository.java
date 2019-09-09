package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,String> {
}
