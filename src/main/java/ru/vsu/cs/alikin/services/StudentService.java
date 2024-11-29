package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.Student;
import ru.vsu.cs.alikin.repositories.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    public static boolean create(Student agr) throws ClassNotFoundException {
        return StudentRepository.getInstance().create(agr) != null;
    }

    public static Student get(Student agr) throws SQLException, ClassNotFoundException {
        return StudentRepository.getInstance().getById(agr);
    }

    public static Student update(Student agr) throws ClassNotFoundException, SQLException {
        return StudentRepository.getInstance().update(agr);
    }

    public static boolean delete(Student agr) throws SQLException, ClassNotFoundException {
        return StudentRepository.getInstance().delete(agr);
    }

    public static List<Student> getAll() {
        return StudentRepository.getInstance().getAll();
    }
}
