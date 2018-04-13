package model;

public class Student {

    private long id;
    private String name;
    private String lastname;
    private int yearOfReg;
    private int points;


    public Student(String name, String lastname, int yearOfReg, int points) {

        this.name = name;
        this.lastname = lastname;
        this.yearOfReg = yearOfReg;
        this.points = points;


    }

    public Student(long id, String name, String lastname, int yearOfReg, int points) {
        this(name,lastname,yearOfReg,points);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getYearOfReg() {
        return yearOfReg;
    }

    public int getPoints() {
        return points;
    }


    public void setId(long id) {
        this.id = id;
    }








}
