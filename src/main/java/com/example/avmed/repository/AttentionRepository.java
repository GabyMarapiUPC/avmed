package com.example.avmed.repository;

import com.example.avmed.model.attention.Attention;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttentionRepository extends MongoRepository<Attention, String> {
    @Query(value = "{healthPersonnelId: ?0}", fields = "{}")
    List<Attention> findByHealthPersonnelId(ObjectId healthPersonnelId);

    @Query(value = "{patientId: ?0}", fields = "{}")
    List<Attention> findByPatientId(ObjectId patientId);

    @Query(value = "{patientId:  {$in: ?0}}", fields = "{}")
    List<Attention> findByPatientsId(List<ObjectId> userId);

}
