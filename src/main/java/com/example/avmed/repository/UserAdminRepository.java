package com.example.avmed.repository;

import com.example.avmed.model.UserAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends MongoRepository<UserAdmin, String> {
    @Query(value = "{username: ?0, password: ?1}", fields = "{}")
    UserAdmin findByUsernameAndPassword(String username, String password);

}
