package com.noname.hw;

import com.noname.hw.sort.BirthDateComparator;
import com.noname.hw.sort.GenderComparator;
import com.noname.hw.sort.LastNameComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/records")
public class HwApplication {

	static Logger logger = LoggerFactory.getLogger(HwApplication.class);

	List<Person> personList = new ArrayList<>();

	public static void main(String[] args) throws ParseException, IOException {
		SpringApplication.run(HwApplication.class, args);

		// first check to see if the program was run with the command line argument
		if (args.length < 1) {
			logger.info("Error - Usage: java <input_file>");
			System.exit(1);
		}

		// read file passed as first argument
		File file = new File(args[0]);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		List<Person> personList = new ArrayList<>();
		String record;
		while ((record = bufferedReader.readLine()) != null) {
			// Use simple factory to create a person object from data line record
			Person person = PersonFactory.createPerson(record);

			// populate person object into a list
			personList.add(person);
		}

		if (!personList.isEmpty()) {
			// Unsorted original list of records collected from input file
			logger.info("----------| Unsorted |----------");
			personList.forEach(myPojo -> logger.info(myPojo.toString()));

			// Sort by gender (females before males) then by last name ascending
			logger.info("----------| Sorted by Gender (females before males) then by last name ascending |----------");
			Collections.sort(personList, new GenderComparator());
			personList.forEach(myPojo -> logger.info(myPojo.toString()));

			// Sorted by birth date, ascending
			logger.info("----------| SORTED by Birth Date ascending |----------");
			Collections.sort(personList, new BirthDateComparator());
			personList.forEach(myPojo -> logger.info(myPojo.toString()));

			// Sorted by last name, descending
			logger.info("----------| Sorted by Last Name descending |----------");
			Collections.sort(personList, new LastNameComparator());
			personList.forEach(myPojo -> logger.info(myPojo.toString()));
		}
	}

	// POST /records - Post a single data line in any of the 3 formats supported
	@PostMapping
	public ResponseEntity<Person> post(@RequestBody String record) throws ParseException {
		Person person = PersonFactory.createPerson(record);
		personList.add(person);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	// GET /records/gender - Gets records sorted by gender
	@GetMapping("/gender")
	public @ResponseBody ResponseEntity<List<Person>> getRecordsSortedByGender() {
		Collections.sort(personList, new GenderComparator());
		return new ResponseEntity<>(personList, HttpStatus.OK);
	}

	// GET /records/birthdate - Gets records sorted by birth date
	@GetMapping("/birthdate")
	public @ResponseBody ResponseEntity<List<Person>> getRecordsSortedByBirthDate() {
		Collections.sort(personList, new BirthDateComparator());
		return new ResponseEntity<>(personList, HttpStatus.OK);
	}

	// GET /records/name - Gets records sorted by last name
	@GetMapping("/name")
	public @ResponseBody ResponseEntity<List<Person>> getRecordsSortedByName() {
		Collections.sort(personList, new LastNameComparator());
		return new ResponseEntity<>(personList, HttpStatus.OK);
	}
}