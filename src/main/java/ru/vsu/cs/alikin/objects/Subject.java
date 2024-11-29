package ru.vsu.cs.alikin.objects;

public class Subject implements Identifiable {
    private int id;
    private String name;

    public Subject(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Subject() {}

    @Override
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
