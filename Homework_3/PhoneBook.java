package Homework_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PhoneBook{
    private static final HashMap<String, ArrayList<Person>> phonebook = new HashMap<>();

    public static void main(String[] args) {
        phonebook.put("Иванов", new ArrayList<Person>(Arrays.asList(new Person("44-55-69", "ivanov@gmail.com"),
                new Person("51-24-89", "ivanov@yahoo.co.jp"))));
        phonebook.put("Петров",new ArrayList<Person>(Arrays.asList(new Person("48-51-22", "petrov@gmail.com"))));
        phonebook.put("Сидоров",new ArrayList<Person>(Arrays.asList(new Person("94-52-34", "petrov@gmail.com"),
                new Person("64-23-51", "petrov@yahoo.co.jp"), new Person("454812", "petrov@mail.ru"))));

        System.out.println("Телефоны Иванова:" + getPhoneByName("Иванов"));
        System.out.println("Почты Иванова: " + getEmailByName("Иванов") + "\n");

        System.out.println("Телефоны Петрова: " + getPhoneByName("Петров"));
        System.out.println("Почты Петрова: "+ getEmailByName("Петров") + "\n");

        System.out.println("Телефоны Сидорова: " + getPhoneByName("Сидоров"));
        System.out.println("Почты Сидорова: " + getEmailByName("Сидоров"));
    }

    public static ArrayList<String> getPhoneByName(String personName){
        ArrayList<String> phones = new ArrayList<String>();

        for (Person p : phonebook.get(personName)) {
            phones.add(p.personPhone);
        }

        return phones;
    }

    public static ArrayList<String> getEmailByName(String personName){
        ArrayList<String> emails = new ArrayList<String>();

        for (Person p : phonebook.get(personName)) {
            emails.add(p.personEmail);
        }

        return emails;
    }
}
