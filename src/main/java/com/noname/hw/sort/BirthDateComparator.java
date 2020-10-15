package com.noname.hw.sort;

import com.noname.hw.Person;
import java.util.Comparator;

public class BirthDateComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return Comparator.comparing(Person::getDateOfBirth)
                .compare(o1, o2);
    }
}