package com.example.avmed.repository;

import com.example.avmed.model.Patient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    @Query(value = "{documentNumber: ?0}", fields = "{}")
    Patient findByDocumentNumber(String documentNumber);

    @Query(value = "{insuredCode: ?0}", fields = "{}")
    Patient findByInsuredCode(String insuredCode);

    @Query(value = "{clinicalHistoryNumber: ?0}", fields = "{}")
    Patient findByClinicalHistoryNumber(String clinicalHistoryNumber);

    @Query(value = "{documentNumber: ?0, insuredCode: ?1}", fields = "{}")
    Patient findByDocumentNumberAndInsuredCode(String documentNumber, String insuredCode);



    @Query(value = "{userId: ?0}", fields = "{}")
    List<Patient> findByUserId(ObjectId userId);

    @Query(value = "{ $or: [ { documentNumber: ?0 }, { insuredCode: ?1 }]}", fields = "{}")
    List<Patient> getPatientsByDocumentNumberOrInsuredCode(String documentNumber, String insuredCode);


}
