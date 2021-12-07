package ch.coolsmtp;

import ch.coolsmtp.mail.Group;
import ch.coolsmtp.mail.Person;

public class App {
    public static void main(String[] args) {
        Person p0 = new Person(0, "coucou@gmail.com");
        Person p1 = new Person(1, "coucou@gmail.com");
        Person p2 = new Person(2, "coucou@gmail.com");
        Person p3 = new Person(3, "coucou@gmail.com");
        Person p4 = new Person(4, "coucou@gmail.com");

        Group g0 = new Group(0, p0, p1, p2, p3);
        System.out.println(g0);
    }
}
