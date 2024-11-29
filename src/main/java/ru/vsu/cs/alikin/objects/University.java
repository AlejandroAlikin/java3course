package ru.vsu.cs.alikin.objects;

import java.util.Date;

public class University implements Identifiable {
    private int universityId;
    private String name;
    private String city;
    private String address;
    private Date yearOfCreation;

    public University(int universityId, String name, String city, String address, Date yearOfCreation) {
        this.universityId = universityId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.yearOfCreation = yearOfCreation;
    }

    public University() {}

    public int getId() {
        return universityId;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public java.sql.Date getYearOfCreation() {
        return (java.sql.Date) yearOfCreation;
    }

    public void setId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setYearOfCreation(Date yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    @Override
    public String toString() {
        return "University {id=" + universityId + ", name='" + name + "', city='" + city + "', address=" + address + ", year_of_creation=" + yearOfCreation + "'}";
    }
}
