package com.example.mainservice.Repository;


import com.example.mainservice.Entity.Description;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MogoDesRepository extends MongoRepository<Description, String> {
    Description findDescriptionBy_id(String id);
}

