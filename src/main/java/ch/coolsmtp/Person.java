package ch.coolsmtp;

public class Person {
    private int no;
    private String address;

    public Person(int no, String address) {
        this.no = no;
        this.address = address;
    }

    public String toString() {
        return String.format("Person %d with Address '%s'", no, address);
    }
}
