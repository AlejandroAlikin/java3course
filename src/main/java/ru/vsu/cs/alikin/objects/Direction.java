package ru.vsu.cs.alikin.objects;

public class Direction implements Identifiable {
    private int id;
    private String qualification;
    private int facultyId;
    private String name;

    public Direction(int id, String name, String qualification, int facultyId) {
        this.id = id;
        this.facultyId = facultyId;
        this.qualification = qualification;
        this.name = name;
    }

    public Direction() {}

    public int getId() {
        return id;
    }

    public String getQualification() {
        return qualification;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public void setId(int directionId) {
        this.id = directionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return "Direction {id=" + id + ", name='" + name + "', qualification='" + qualification + "', faculty_id=" + facultyId + "'}";
    }
}
