package com.example.avmed.repository;

import com.example.avmed.model.appointment.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    @Query(value = "{healthPersonnelId: ?0}", fields = "{}")
    List<Appointment> findByHealthPersonnelId(ObjectId healthPersonnelId);

    @Query(value = "{patientId:  {$in: ?0}}", fields = "{}")
    List<Appointment> findByPatientIds(List<ObjectId> userId);

}
