package com.programming.ecommerce.repository;
import com.programming.ecommerce.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  User findAllByUsername(String usn);
  User findUserById(String userId);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  User save(User user);
}
