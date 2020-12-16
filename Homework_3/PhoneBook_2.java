package Homework_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PhoneBook_2 {
    protected HashMap<String, ArrayList<Person_2>> phonebook;

    PhoneBook_2() {
        phonebook = new HashMap<String, ArrayList<Person_2>>();
    }

    protected void addPerson(Person_2 person) {
        ArrayList<Person_2> p = new ArrayList<Person_2>();
        if (phonebook.containsKey(person.personLastName)) {
            p = phonebook.get(person.personLastName);
            p.add(person);
            phonebook.replace(person.personLastName, p);
        } else{
            p.add(person);
            phonebook.put(person.personLastName, p);
        }
    }

    protected ArrayList<String> getPhonesByLastName(String personLastName){
        ArrayList<String> phones = new ArrayList<String>();

        if (phonebook.containsKey(personLastName)){
            for (Person_2 p : phonebook.get(personLastName)) {
                phones.add(p.personLastName + " " + p.personFirstName + " " + p.personMiddleName + ": " + p.personPhone);
            }
        }

        else {
            phones.add("No entry found by: " + personLastName);
        }

        return phones;
    }

    protected ArrayList<String> getEmailsByLastName(String personLastName){
        ArrayList<String> emails = new ArrayList<String>();
        if (phonebook.containsKey(personLastName)){
            for (Person_2 p : phonebook.get(personLastName)) {
                emails.add(p.personLastName + " " + p.personFirstName + " " + p.personMiddleName + ": " + p.personEmail);
            }
        }

        else {
            emails.add("No entry found by: " + personLastName);
        } 

        return emails;
    }
}

