package Homework_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String[] words = {
                "Создать", "массив", "набором", "слов", "слов", "должны", "встречаться", "повторяющиеся",
                "Найти", "список", "слов", "которых", "состоит", "текст", "дубликаты", "считать",
                "Посчитать", "сколько", "раз", "встречается", "каждое", "слово", "использовать", "HashMap",
                "Создать", "дубликаты", "считать", "повторяющиеся", "сколько", "раз", "встречается", "слово"
        };

        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
        System.out.println(wordList + "\n");

        HashMap<String, Integer> wordMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (wordMap.containsKey(words[i])){
                count = wordMap.get(words[i]);
                wordMap.replace(words[i], ++count);
            }
                else
                    wordMap.put(words[i], 1);
        }

        System.out.println(wordMap.toString() + "\n");

        PhoneBook_2 phonebook = new PhoneBook_2();
        phonebook.addPerson(new Person_2("Ivanov", "Ivan", "Ivanovich",
                "45-45-45", "Ivanov@gmail.com"));
        phonebook.addPerson(new Person_2("Petrov", "Pyotr", "Petrovich",
                "65-65-65", "Petrov@gmail.com"));
        phonebook.addPerson(new Person_2("Kozlov", "Koz--", "Kozlovich",
                "25-25-25", "Kozlov@gmal.com"));
        phonebook.addPerson(new Person_2("Ivanov", "Alexei", "Vasilievich",
                "35-35-35", "IvanovAV@yahoo.co.jp"));

        System.out.println(phonebook.getPhonesByLastName("Ivanov"));
        System.out.println(phonebook.getEmailsByLastName("Ivanov"));

        System.out.println(phonebook.getPhonesByLastName("Kozlov"));
        System.out.println(phonebook.getEmailsByLastName("Petrov"));

        System.out.println(phonebook.getPhonesByLastName("Sidorov"));
        System.out.println(phonebook.getEmailsByLastName("Sidorov"));
    }
}
