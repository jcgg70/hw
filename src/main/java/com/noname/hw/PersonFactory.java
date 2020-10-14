package com.noname.hw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonFactory {

    static final String DATE_FORMAT = "M/d/yyyy";

    public static Person createPerson(String record) throws ParseException {

        // Handle 3 types of delimiters on the input text file
        String delims = "[ |, ]+";
        String[] tokens = record.split(delims);

        String firstName = tokens[0];
        String lastName = tokens[1];
        String gender = tokens[2].toUpperCase();
        String favoriteColor = tokens[3];
        Date dateOfBirth = getDateOfBirth(tokens[4]);
        return new Person(firstName, lastName, Gender.valueOf(gender), favoriteColor, dateOfBirth);
    }

    private static Date getDateOfBirth(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(date);
    }

}
