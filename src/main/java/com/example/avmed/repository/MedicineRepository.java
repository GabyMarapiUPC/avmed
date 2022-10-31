package com.example.avmed.repository;

import com.example.avmed.model.attention.Diagnosis;
import com.example.avmed.model.attention.Medicine;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine, String> {
    @Query(value = "{_id:  {$in: ?0}}", fields = "{}")
    List<Medicine> findByMedicineIds(List<ObjectId> medicineIds);
}
