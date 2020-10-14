package com.noname.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class HwApplication {

	public static void main(String[] args) throws ParseException, IOException {
		SpringApplication.run(HwApplication.class, args);

		// first check to see if the program was run with the command line argument
		if (args.length < 1) {
			System.out.println("Error - Usage: java <input_file>");
			System.exit(1);
		}

		File file = new File(args[0]);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		List<Person> personList = new ArrayList<>();
		String record;
		while ((record = bufferedReader.readLine()) != null) {
			Person person = PersonFactory.createPerson(record);

			// populate person object into a list
			personList.add(person);
		}

		if (!personList.isEmpty()) {

			// Sort by gender (females before males) then by last name ascending
			Comparator<Person> genderComparator = Comparator.comparing(Person::getGender)
					.thenComparing(Person::getLastName)
					.reversed();

			// Sorted by birth date, ascending
			Comparator<Person> birthDateComparator = Comparator.comparing(Person::getDateOfBirth);

			// Sorted by last name, descending
			Comparator<Person> lastNameComparator = Comparator.comparing(Person::getLastName)
					.reversed();

			//
			System.out.println("----------| Unsorted |----------");
			personList.forEach(System.out::println);

			//
			System.out.println("----------| Sorted by Gender |----------");
			Collections.sort(personList, genderComparator);
			personList.forEach(System.out::println);

			//
			System.out.println("----------| SORTED by Birth Date |----------");
			Collections.sort(personList, birthDateComparator);
			personList.forEach(System.out::println);

			//
			System.out.println("----------| Sorted by Last Name |----------");
			Collections.sort(personList, lastNameComparator);
			personList.forEach(System.out::println);

		}



	}

}
