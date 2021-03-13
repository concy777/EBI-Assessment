package com.ebi.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebi.assessment.model.PersonEntity;
 
@Repository
public interface PersonRepository
        extends JpaRepository<PersonEntity, Long> {
 
}
