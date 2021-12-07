package ch.coolsmtp.mail;

public class Person {
    private int no;
    private String address;

    public Person(int no, String address) {
        this.no = no;
        this.address = address;
    }

    public void setFromFile() {

    }

    public String toString() {
        return String.format("Person %d with Address '%s'", no, address);
    }
}
