package ru.vsu.cs.alikin;

import ru.vsu.cs.alikin.objects.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private static Repository<University> universityRepository = new Repository<>();
    private static Repository<Faculty> facultyRepository = new Repository<>();
    private static Repository<Direction> directionRepository = new Repository<>();
    private static Repository<Student> studentRepository = new Repository<>();
    private static Repository<Post> postRepository = new Repository<>();
    private static Repository<Lecturer> lecturerRepository = new Repository<>();
    private static Repository<Subject> subjectRepository = new Repository<>();


    public Controller() {
    }

    public University addUniversity(String name, String city, String address, Date yearOfCreation) {
        var university = new University(-1, name, city, address, yearOfCreation);
        return universityRepository.save(university);
    }

    public Faculty addFaculty(String name, int yearOfCreation, int universityId) {
        var faculty = new Faculty(-1, name, yearOfCreation, universityId);
        if (universityRepository.findByID(universityId) != null) {
            return facultyRepository.save(faculty);
        } else {
            throw new IllegalArgumentException("University does not exist");
        }
    }

    public Direction addDirection(String name, String qualification, int facultyId) {
        var direction = new Direction(-1, name, qualification, facultyId);
        if (facultyRepository.findByID(facultyId) != null) {
            return directionRepository.save(direction);
        } else {
            throw new IllegalArgumentException("Faculty does not exist");
        }
    }

//    public Student addStudent(String name, String surname, String patronymic, Date birthDay, int directionId, int universityId) {
//        var student = new Student(-1, name, surname, patronymic, birthDay, directionId, universityId);
//        if (universityRepository.findByID(universityId) != null && directionRepository.findByID(universityId) != null) {
//            return studentRepository.save(student);
//        } else {
//            throw new RuntimeException("University or Direction does not exist");
//        }
//    }

    public Post addPost(String name, double salary, int universityId) {
        var post = new Post(-1, name, salary, universityId);
        if (universityRepository.findByID(universityId) != null) {
            return postRepository.save(post);
        } else {
            throw new RuntimeException("University does not exist");
        }
    }

    public Lecturer addLecturer(String name, String surname, String patronymic, int postId) {
        var lecturer = new Lecturer(-1, name, surname, patronymic, postId);
        if (postRepository.findByID(postId) != null) {
            return lecturerRepository.save(lecturer);
        } else {
            throw new RuntimeException("Post does not exist");
        }
    }

    public Subject addSubject(String name) {
        var subj = new Subject(-1, name);
        return subjectRepository.save(subj);
    }

    public boolean removeUniversityById(int universityId) {
        return universityRepository.deleteById(universityId);
    }

    public boolean removeFacultyById(int facultyId) { return facultyRepository.deleteById(facultyId); }

    public boolean removeStudentById(int studentId) { return studentRepository.deleteById(studentId); }

    public boolean removePostById(int postId) { return postRepository.deleteById(postId); }

    public boolean removeLecturerById(int lecturerId) { return lecturerRepository.deleteById(lecturerId); }

    public boolean removeSubjectById(int subjectId) { return subjectRepository.deleteById(subjectId); }

    public boolean removeDirectoryById(int directoryId) { return directionRepository.deleteById(directoryId); }

    public List<University> getAllUniversity() { return new ArrayList<>(universityRepository.findAll()); }

    public List<Faculty> getAllFaculty() { return new ArrayList<>(facultyRepository.findAll()); }

    public List<Direction> getAllDirections() { return new ArrayList<>(directionRepository.findAll()); }

    public List<Post> getAllPosts() { return new ArrayList<>(postRepository.findAll()); }

    public List<Lecturer> getAllLecturers() { return new ArrayList<>(lecturerRepository.findAll()); }

    public List<Subject> getAllSubjects() { return new ArrayList<>(subjectRepository.findAll()); }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }

    public Student getStudentById(int studentId) { return studentRepository.findByID(studentId); }

    public Post getPostById(int postId) { return postRepository.findByID(postId); }

    public Faculty getFacultyById(int facultyId) { return facultyRepository.findByID(facultyId); }

    public Direction getDirectionById(int directionId) { return directionRepository.findByID(directionId); }

    public Subject getSubjectById(int subjectId) { return subjectRepository.findByID(subjectId); }

    public Lecturer getLecturerById(int lecturerId) { return lecturerRepository.findByID(lecturerId); }

    public University getUniversityById(int universityId) { return universityRepository.findByID(universityId); }
}