package models;

import java.util.Random;
import java.util.UUID;

public class InputGeneration {

    public static String generateRandomEmail() {
        String user = "user" + UUID.randomUUID().toString().substring(0, 6);
        String[] domains = {"example.com", "testmail.com", "email.com"};
        String domain = domains[new Random().nextInt(domains.length)];
        return user + "@" + domain;
    }

    public static String generateRandomName() {
        return "user" + UUID.randomUUID().toString().substring(0, 4);
    }

    public static String generateRandomPhoneNumber() {
        return "0" + UUID.randomUUID().toString().substring(0,8);
    }

    public static String generateRandomZip(){
        return "4" + UUID.randomUUID().toString().substring(0,5);
    }
}
