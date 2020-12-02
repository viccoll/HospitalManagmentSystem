package Models;

public class Address {
    private int id;
    private String street;
    private int flatNumber;
    private int houseNumber;
    private int corpus;

    public Address(String street, int flatNumber, int houseNumber,
                   int corpus) {
        this.street = street;
        this.flatNumber = flatNumber;
        this.houseNumber = houseNumber;
        this.corpus = corpus;
    }

    public Address() {
    }
}
