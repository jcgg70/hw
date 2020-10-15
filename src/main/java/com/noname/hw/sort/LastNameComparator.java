package com.noname.hw.sort;

import com.noname.hw.Person;
import java.util.Comparator;

public class LastNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Comparator.comparing(Person::getLastName)
                .reversed()
                .compare(o1, o2);
    }
}
