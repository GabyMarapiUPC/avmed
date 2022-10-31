package com.example.avmed.repository;

import com.example.avmed.model.Patient;
import com.example.avmed.model.attention.Attention;
import com.example.avmed.model.attention.Diagnosis;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends MongoRepository<Diagnosis, String> {
    @Query(value = "{_id:  {$in: ?0}}", fields = "{}")
    List<Diagnosis> findByDiagnosesIds(List<ObjectId> diagnosisIds);

}
