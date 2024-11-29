package ru.vsu.cs.alikin.objects;

import java.util.Date;

public class Student implements  Identifiable {
    private int studentId;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDay;
    private int directionId;

    public Student() {
    }

    public Student(int studentId, String name, String surname, String patronymic, Date birthDay, int directionId) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
        this.directionId = directionId;
    }

    public int getId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public java.sql.Date getBirthDay() {
        return (java.sql.Date) birthDay;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student {id=" + studentId + ", name='" + name + "', surname='" + surname + "', patronymic=" + patronymic + ", birthday=" + birthDay
                 + ", direction_id='" + directionId + "'}";
    }
}
