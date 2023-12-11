package com.example.springbootjwt.Repository;

import com.example.springbootjwt.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    @Query("{email: \"?0\"}")
    List<User> getUserByEmail(String email);
}
