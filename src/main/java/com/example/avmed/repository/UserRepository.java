package com.example.avmed.repository;

import com.example.avmed.model.User;
import com.example.avmed.model.UserAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{phoneNumber: ?0}", fields = "{}")
    User findByPhoneNumber(String phoneNumber);
}
