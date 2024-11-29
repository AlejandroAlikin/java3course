package ru.vsu.cs.alikin.objects;

public class Post implements Identifiable {
    private int id;
    private String name;
    private double salary;
    private int univId;

    public Post(int id, String name, double salary, int univId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.univId = univId;
    }

    public Post() {}

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getUnivId() {
        return univId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUnivId(int univId) {
        this.univId = univId;
    }
}
