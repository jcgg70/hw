package com.noname.hw;

import com.noname.hw.sort.BirthDateComparator;
import com.noname.hw.sort.GenderComparator;
import com.noname.hw.sort.LastNameComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = HwApplication.class)

@AutoConfigureMockMvc

public class HwApplicationTests {

	static List<Person> persons = new ArrayList<>();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@BeforeAll
	static void initAll() throws ParseException {
		Person person1 = PersonFactory.createPerson("Andrade|Ava-Rose|female|Cyan|12/21/1988");
		Person person2 = PersonFactory.createPerson("Riggs,Rohan,female,White,11/30/2016");
		Person person3 = PersonFactory.createPerson("Simmonds Adyan male Maroon 10/3/1984");
		Person person4 = PersonFactory.createPerson("Portillo|Miranda|female|Orange|1/31/1925");
		Person person5 = PersonFactory.createPerson("Worthington,Zaid,male,Green,6/8/1947");
		Person person6 = PersonFactory.createPerson("Reeves Martin male Brown 7/2/1969");
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);
		persons.add(person6);
	}

	@Test
	public void testPostRecord() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/records")
				.contentType(MediaType.APPLICATION_JSON)
				.content("Howell|Rian|female|Beige|12/13/1924"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Rian"));
	}

 	@Test
	public void testGetRecordsByGender() throws Exception {
		mockMvc.perform(get("/records/gender")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetRecordsByBirthdate() throws Exception {
		mockMvc.perform(get("/records/birthdate")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

	@Test
	public void testGetRecordsByLastName() throws Exception {
		mockMvc.perform(get("/records/name")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testCreatePersonFactoryWithCommaDelimiter() throws ParseException {
		Person person = PersonFactory.createPerson("Reeves,Martin,male,Brown,7/2/1969");
		assertEquals("Reeves", person.getLastName());
 	}

	@Test
	public void testCreatePersonFactoryWithPipeDelimiter() throws ParseException {
		Person person = PersonFactory.createPerson("Andrade|Ava-Rose|female|Cyan|12/21/1988");
		assertEquals("Cyan", person.getFavoriteColor());
	}

	@Test
	public void testCreatePersonFactoryWithSpaceDelimiter() throws ParseException {
		Person person = PersonFactory.createPerson("Riggs Rohan female White 11/30/2016");
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		assertEquals("11/30/2016", formatter.format(person.getDateOfBirth()));
	}

	@Test
	public void testSortByGender() {
		Collections.sort(persons, new GenderComparator());
		assertEquals(Gender.FEMALE, persons.get(0).getGender());
		assertEquals("Andrade", persons.get(0).getLastName());
		//System.out.println("--- test sort by gender ---");
		//persons.forEach(System.out::println);
	}

	@Test
	public void testSortByBirthDate() {
		Collections.sort(persons, new BirthDateComparator());
		assertEquals("Portillo", persons.get(0).getLastName());
		//System.out.println("--- test sort by birth date ---");
		//persons.forEach(System.out::println);
	}

	@Test
	public void testSortByLastName() {
		Collections.sort(persons, new LastNameComparator());
		assertEquals("Worthington", persons.get(0).getLastName());
		assertEquals("Andrade", persons.get(5).getLastName());
		//System.out.println("--- test sort by last name ---");
		//persons.forEach(System.out::println);
	}

}