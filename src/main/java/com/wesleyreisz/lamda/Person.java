package com.wesleyreisz.lamda;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by reiszw on 6/2/14.
 *
 * Following tutorial at http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html on lambdas
 */
public class Person {
    public enum Sex{
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(this.getBirthday(),now);
        return age.getYears();
    }

    public static  List<Person> createRoster(){
        List<Person> persons = new ArrayList<Person>();
        Person person = new Person("Wes",new LocalDate(1972,3,14),Sex.MALE,"wes@wesleyreisz.com");
        persons.add(person);

        person = new Person("Kim",new LocalDate(1975,5,18),Sex.FEMALE,"kimberlympreston@gmail.com");
        persons.add(person);

        person = new Person("Tyler",new LocalDate(2002,6,20),Sex.MALE,"baldeagle7@gmail.com");
        persons.add(person);

        person = new Person("Leanne",new LocalDate(1994,5,18),Sex.FEMALE,"leanne@wesleyreisz.com");
        persons.add(person);

        person = new Person("Justin",new LocalDate(1999,4,26),Sex.MALE,"justin@wesleyreisz.com");
        persons.add(person);

        person = new Person("Jake",new LocalDate(1990,9,14),Sex.MALE,"jake@wesleyreisz.com");
        persons.add(person);

        person = new Person("Sam",new LocalDate(1952,3,20),Sex.MALE,"same@yahoo.com");
        persons.add(person);

        person = new Person("Sarah",new LocalDate(1988,9,13),Sex.FEMALE,"sarah@gmail.com");
        persons.add(person);

        person = new Person("Lori",new LocalDate(1975,3,5),Sex.FEMALE,"lori@wesleyreisz.com");
        persons.add(person);

        person = new Person("Katherine",new LocalDate(1975,3,5),Sex.FEMALE,"katherine@wesleyreisz.com");
        persons.add(person);

        return persons;
    }

    public String printPerson(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name: %s [%s]\n", this.getName(), this.emailAddress));
        stringBuilder.append(String.format(" - %s\n", this.getBirthday()));
        stringBuilder.append(String.format(" - %s\n", this.getGender().toString()));

        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    public void printPersonNoReturn(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name: %s [%s]\n", this.getName(), this.emailAddress));
        stringBuilder.append(String.format(" - %s\n", this.getBirthday()));
        stringBuilder.append(String.format(" - %s\n", this.getGender().toString()));

        System.out.println(stringBuilder);
    }

    @Override
    public String toString() {
        return this.printPerson();
    }

    //Approach 1: Create Methods That Search for Members That Match One Characteristic
    public static List<Person> printPersonsOlderThan(List<Person> roster, int age) {
        List<Person> persons = new ArrayList<Person>();
        for (Person p : roster) {
            if (p.getAge() >= age) {
                persons.add(p);
                p.printPerson();
            }
        }
        return persons;
    }

    //Approach 2: Create More Generalized Search Methods
    public static List<Person> printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
        List<Person> persons = new ArrayList<Person>();
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                persons.add(p);
                p.printPerson();
            }
        }
        return persons;
    }

    //Approach 3: Specify Search Criteria Code in a Local Class
    public static List<Person> printPersons(List<Person> roster, CheckPerson tester) {
        List<Person> persons = new ArrayList<Person>();
        for (Person p : roster) {
            if (tester.test(p)) {
                persons.add(p);
                p.printPerson();
            }
        }
        return persons;
    }

    //Approach 4 and 5 are listed in the testcase

    //Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    public static void printPersonsWithPredicate(
            List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    //Approach 7: Use Lambda Expressions Throughout Your Application
    public static void processPersons(
            List<Person> roster,
            Predicate<Person> tester,
            Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }
}
