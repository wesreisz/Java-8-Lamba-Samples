package com.wesleyreisz.lamda;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by reiszw on 6/2/14.
 */
public class PersonTest {
    @Test
    public void testCreateRoster(){
        List<Person> personList = Person.createRoster();
        Assert.assertNotNull(personList);
        Assert.assertEquals(true,personList.size()>1);
    }
    @Test
    public void testPrintRecord(){
        List<Person> personList = Person.createRoster();
        Person person = personList.get(personList.size()-1);
        Assert.assertNotNull(person.printPerson());
    }


    /**
     * Approach 1: Create Methods That Search for Members That Match One Characteristic
     * Demonstrates one method of finding information from within a list.
     */
    @Test
    public void testApproach1(){
        List<Person> personList = Person.printPersonsOlderThan(Person.createRoster(),20);
        Assert.assertNotNull(personList);
        Assert.assertEquals(8,personList.size());
    }

    /**
     * Approach 2: Create More Generalized Search Methods
     * Demonstrates the brittleness of the first approach by adding an additional argument
     * to filter on.s
     */
    @Test
    public void testApproach2() {
        List<Person> personList = Person.printPersonsWithinAgeRange(Person.createRoster(), 20, 30);
        Assert.assertNotNull(personList);
        Assert.assertEquals(3, personList.size());
    }

    /**
     * Approach 3: Specify Search Criteria Code in a Local Class
     * Abstracts away the implementation of the matcher
     */
    @Test
    public void testApproach3(){
        List<Person> personList = Person.printPersons(Person.createRoster(), new CheckPersonEligibleForSelectiveService());
        Assert.assertNotNull(personList);
        Assert.assertEquals(1, personList.size());
    }

    /**
     * Approach 4: Specify Search Criteria Code in an Anonymous Class
     * Implemented with an anonymous to reduce amount of code required.
     */
    @Test
    public void testApproach4(){
        List<Person> personList = Person.printPersons(
            Person.createRoster(),
            new CheckPerson() {
                @Override
                public boolean test(Person person) {
                    return person.getGender() == Person.Sex.MALE
                            && person.getAge() >= 18
                            && person.getAge() <= 25;
                }
            }
        );

        Assert.assertNotNull(personList);
        Assert.assertEquals(1, personList.size());
        Assert.assertEquals(Person.printPersons(
                Person.createRoster(), new CheckPersonEligibleForSelectiveService()).size(),
                personList.size()
        );
    }
    /*
    Approach 5: Specify Search Criteria Code with a Lambda Expression
    A functional interface may contain one or more default methods or static methods. Because a functional
    interface contains only one abstract method, you can omit the name of that method when you implement it.
    To do this, instead of using an anonymous class expression, you use a lambda expression
    */
    @Test
    public void testApproach5(){
        List<Person> personList = Person.printPersons(
            Person.createRoster(),
            (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >=18
                && p.getAge() <=25
        );

        Assert.assertNotNull(personList);
        Assert.assertEquals(1, personList.size());
    }

    /**
     * Approach 6: Use Standard Functional Interfaces with Lambda Expressions
     * You can use the Predicate<T> interface in place of CheckPerson. This interface contains the method boolean
     * test(T t):
         interface Predicate<T> {
            boolean test(T t);
         }

       The interface Predicate<T> is an example of a generic interface.
     */
    @Test
    public void testApproach6(){
        List<Person> personList = Person.printPersons(
            Person.createRoster(),
            p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >=18
                && p.getAge() <=25
        );

        Assert.assertNotNull(personList);
        Assert.assertEquals(1, personList.size());
    }
    /*
    Approach 7: Use Lambda Expressions Throughout Your Application
    Not working... stopped here

    @Test
    public void testApproach7(){
        List<Person> personList = Person.printPersons(
            Person.createRoster(),
            p -> p.getGender() == Person.Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25,
            p -> p.printPersonNoReturn()
        );
    }
     */
}
