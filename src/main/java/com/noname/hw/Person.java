package com.noname.hw;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String favoriteColor;
    private Date dateOfBirth;

    public Person(String firstName, String lastName, Gender gender, String favoriteColor, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.favoriteColor = favoriteColor;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");

        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", favoriteColor='" + favoriteColor + '\'' +
                ", dateOfBirth=" + formatter.format(dateOfBirth) +
                '}';
    }
}
