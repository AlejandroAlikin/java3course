package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.Subject;
import ru.vsu.cs.alikin.repositories.SubjectRepository;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {
    public static boolean create(Subject agr) throws ClassNotFoundException {
        return SubjectRepository.getInstance().create(agr) != null;
    }

    public static Subject get(Subject agr) throws SQLException, ClassNotFoundException {
        return SubjectRepository.getInstance().getById(agr);
    }

    public static Subject update(Subject agr) throws ClassNotFoundException, SQLException {
        return SubjectRepository.getInstance().update(agr);
    }

    public static boolean delete(Subject agr) throws SQLException, ClassNotFoundException {
        return SubjectRepository.getInstance().delete(agr);
    }

    public static List<Subject> getAll() {
        return SubjectRepository.getInstance().getAll();
    }
}
