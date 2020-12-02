package Models;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private int id;
    private Street street;
    private int flatNumber;
    private int houseNumber;
    private int corpus;

    public int getId() {
        return id;
    }


    public int getFlatNumber() {
        return flatNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getCorpus() {
        return corpus;
    }


    public Address(Street street, int flatNumber, int houseNumber,
                   int corpus) {
        this.street = street;
        this.flatNumber = flatNumber;
        this.houseNumber = houseNumber;
        this.corpus = corpus;
    }

    public Address() {
    }

    public Street getStreet() {
        return street;
    }
}
