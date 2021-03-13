package com.ebi.assessment.web;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebi.assessment.exception.RecordNotFoundException;
import com.ebi.assessment.model.PersonEntity;
import com.ebi.assessment.service.PersonService;
 
@RestController
@RequestMapping("/persons")
public class PersonController
{
    @Autowired
    PersonService service;
 
    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        List<PersonEntity> list = service.getAllPersons(); 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "persons");

       
        return new ResponseEntity<List<PersonEntity>>(list, headers, HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
       
    	
    	 try {
    		 PersonEntity entity = service.getPersonById(id);
    		 return new ResponseEntity<PersonEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    	    } catch (RecordNotFoundException ex) {
    	        // log exception first, then return Not Found (404)
    	       // logger.error(ex.getMessage());
    	        return ResponseEntity.notFound().build();
    	    }
    	
    	
 
       
    }
 
    @PostMapping
    public ResponseEntity<PersonEntity> createOrUpdatePerson(@RequestBody PersonEntity person)
                                                    throws RecordNotFoundException {
        PersonEntity updated = service.createOrUpdatePerson(person);
        
       
        if (updated == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(person.getId()).toUri();
		
		 return ResponseEntity.created(location).build();
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        
    	try {
    		service.deletePersonById(id);
            return ResponseEntity.ok().build();
        } catch (RecordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }
 
}