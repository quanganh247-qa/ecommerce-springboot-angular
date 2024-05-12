package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.enums.ERole;
import com.programming.ecommerce.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
