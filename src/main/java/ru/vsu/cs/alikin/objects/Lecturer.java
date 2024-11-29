package ru.vsu.cs.alikin.objects;

public class Lecturer implements Identifiable {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private int postId;

    public Lecturer(int id, String name, String surname, String patronymic, int postId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.postId = postId;
    }

    public Lecturer() {}

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getPostId() {
        return postId;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
