package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Specialty implements Serializable {
    public static ArrayList<Specialty> listSpecialties = new ArrayList<Specialty>();
    private static final long serialVersionUID = 1113799434508676095L;
    private int id;
    private String name;

    public Specialty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return  id + ". " + name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
