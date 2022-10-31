package com.example.avmed.repository;

import com.example.avmed.model.HealthPersonnel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthPersonnelRepository extends MongoRepository<HealthPersonnel, String> {
    @Query(value = "{userId: ?0}", fields = "{}")
    HealthPersonnel findByUserId(ObjectId userId);

}
