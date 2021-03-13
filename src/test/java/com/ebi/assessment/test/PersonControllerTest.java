package com.ebi.assessment.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.Arrays;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ebi.assessment.model.PersonEntity;
import com.ebi.assessment.service.PersonService;
import com.ebi.assessment.web.PersonController;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
@WithMockUser
@ContextConfiguration
@WebAppConfiguration
public class PersonControllerTest {
	
	@Autowired
	private WebApplicationContext context;


	@Autowired
	private MockMvc mockMvc;
	

	@MockBean
	private PersonService personService;
	

	PersonEntity person = new PersonEntity((long) 3,"Sweety","Saravana",34,"Yellow");
	
	String exampleJson =  "{\"id\":3,\"firstName\":\"Sweety\",\"lastName\":\"Saravana\",\"age\":34,\"favourite_colour\":\"Yellow\"}";
	@Test
	public void getPersons() throws Exception {

		Mockito.when(
				personService.getPersonById(
						Mockito.anyLong())).thenReturn(person);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/persons/3").accept(
				MediaType.APPLICATION_JSON);
		mockMvc
		.perform(formLogin("/persons").user("admin").password("pass"));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":3,\"firstName\":\"Sweety\",\"lastName\":\"Saravana\",\"age\":34,\"favourite_colour\":\"Yellow\"}";


		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void createPerson() throws Exception {
		PersonEntity person = new PersonEntity((long) 3,"Sweety","Saravana",34,"Yellow");
		
		
		Mockito.when(
				personService.createOrUpdatePerson(Mockito.any(PersonEntity.class))).thenReturn(person);

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/persons")
				.accept(MediaType.APPLICATION_JSON).content(exampleJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		

		

	}

}

