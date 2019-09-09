package com.example.inventoryManagement.repository;

import com.example.inventoryManagement.beans.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue,String> {
}
