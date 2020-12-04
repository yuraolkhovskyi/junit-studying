package com.junit.demo.service;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

//@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Person  extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;

    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    public static void main(String[] args) {
        Person p = new Person("", "", LocalDate.now());
        p.start();
        p.start();
        System.out.println(Thread.currentThread().getName());
    }
}
