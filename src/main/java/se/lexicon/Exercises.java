package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
         Predicate<Person> filter = person -> person.getFirstName().equalsIgnoreCase("Erik");
        storage.findMany(filter).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getGender().equals(Gender.FEMALE);
        storage.findMany(filter).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        Predicate<Person> filter = person ->
                person.getBirthDate().equals(birthDate) || person.getBirthDate().isAfter(birthDate);

        storage.findMany(filter).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 123;

        if (storage.findOne(filter) != null) {
            System.out.println(storage.findOne(filter));
        } else {
            System.out.println("Person not found");
        }

        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 456;
        if (storage.findOne(filter) != null) {
            String result = storage.findOneAndMapToString(filter, person -> "Name: " +
                    person.getFirstName() + " " +
                    person.getLastName() + " born " +
                    person.getBirthDate());

            System.out.println(result);
        } else {
            System.out.println("Person not found");
        }

        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getGender().equals(Gender.MALE) &&
                person.getFirstName().startsWith("E");
        Function<Person, String> personToString = person -> "First Name: " + person.getFirstName() + " Last Name: " +
                person.getLastName() + " Gender: " + person.getGender();
        storage.findManyAndMapEachToString(filter, personToString).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getBirthDate().isAfter(LocalDate.now().minusYears(10));
        Function<Person, String> personToString = person -> person.getFirstName() + " " +
                person.getLastName() + " " +
                (LocalDate.now().getYear() - person.getBirthDate().getYear()) + " years";
        storage.findManyAndMapEachToString(filter, personToString).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().equals("Ulf");
        Consumer<Person> consumer = System.out::println;
        storage.findAndDo(filter, consumer);

        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getLastName().contains(person.getFirstName());
        storage.findAndDo(filter, System.out:: println);
        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> new StringBuilder(person.getFirstName()).reverse().toString().equalsIgnoreCase(person.getFirstName());
        Consumer<Person> consumer = person -> System.out.println("First Name: " + person.getFirstName() + " Last Name: " + person.getLastName());
        storage.findAndDo(filter, consumer);

        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().startsWith("A");
        Comparator<Person> comparator = Comparator.comparing(Person::getBirthDate);
        storage.findAndSort(filter, comparator).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getBirthDate().isBefore(LocalDate.of(1950, 1, 1));
        Comparator<Person> comparator = Comparator.comparing(Person::getBirthDate).reversed();
        storage.findAndSort(filter, comparator).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        Comparator<Person> comparator = Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getBirthDate);
        storage.findAndSort(comparator).forEach(System.out::println);
        System.out.println("----------------------");
    }
}
