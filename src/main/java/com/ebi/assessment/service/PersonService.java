package com.ebi.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebi.assessment.exception.RecordNotFoundException;
import com.ebi.assessment.model.PersonEntity;
import com.ebi.assessment.repository.PersonRepository;
 
@Service
public class PersonService {
     
    @Autowired
    PersonRepository repository;
     
    public List<PersonEntity> getAllPersons()
    {
        List<PersonEntity> personList = repository.findAll();
         
        if(personList.size() > 0) {
            return personList;
        } else {
            return new ArrayList<PersonEntity>();
        }
    }
     
    public PersonEntity getPersonById(Long id) throws RecordNotFoundException
    {
        Optional<PersonEntity> person = repository.findById(id);
         
        if(person.isPresent()) {
            return person.get();
        } else {
            throw new RecordNotFoundException("No person record exist for given id");
        }
    }
     
    public PersonEntity createOrUpdatePerson(PersonEntity entity) throws RecordNotFoundException
    {
        Optional<PersonEntity> person = repository.findById(entity.getId());
         
        if(person.isPresent())
        {
            PersonEntity newEntity = person.get();
            
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setAge(entity.getAge());
            newEntity.setFavourite_colour(entity.getFavourite_colour());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deletePersonById(Long id) throws RecordNotFoundException
    {
        Optional<PersonEntity> person = repository.findById(id);
         
        if(person.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No person record exist for given id");
        }
    }
}