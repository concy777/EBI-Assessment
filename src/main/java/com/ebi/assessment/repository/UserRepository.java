package com.ebi.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebi.assessment.model.User;
 
@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

	User findByUsername(String username);
 
}
