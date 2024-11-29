package ru.vsu.cs.alikin.objects;

public class Faculty implements Identifiable {
    private int id;
    private String name;
    private int yearOfCreation;
    private int universityId;

    public Faculty(int id, String name, int yearOfCreation, int universityId) {
        this.id = id;
        this.name = name;
        this.yearOfCreation = yearOfCreation;
        this.universityId = universityId;
    }

    public Faculty() {}

    public String getName() {
        return name;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public int getUniversityId() {
        return universityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int facultyId) {
        this.id = facultyId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty {id=" + id + ", name='" + name + "', year_of_creation='" + yearOfCreation + "', univ_id=" + universityId + "'}";
    }
}
