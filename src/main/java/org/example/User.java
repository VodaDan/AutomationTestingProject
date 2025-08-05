package org.example;

import java.util.Random;
import java.util.UUID;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {
        this.firstName = generateRandomName();
        this.lastName = generateRandomName();
        this.email = generateRandomEmail();
        this.password = generateRandomName();
    }

    public static String generateRandomEmail() {
        String user = "user" + UUID.randomUUID().toString().substring(0, 6);
        String[] domains = {"example.com", "testmail.com", "email.com"};
        String domain = domains[new Random().nextInt(domains.length)];
        return user + "@" + domain;
    }

    public static String generateRandomName() {
        return "user" + UUID.randomUUID().toString().substring(0, 4);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
