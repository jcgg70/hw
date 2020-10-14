package com.noname.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.text.ParseException;

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
		String record;
		while ((record = bufferedReader.readLine()) != null) {
			Person person = PersonFactory.createPerson(record);

			// TODO: list or Set to be sorted

			System.out.println(person);
		}

	}

}
