package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.Faculty;
import ru.vsu.cs.alikin.repositories.FacultyRepository;

import java.sql.SQLException;
import java.util.List;

public class FacultyService {
    public static boolean create(Faculty agr) throws ClassNotFoundException {
        return FacultyRepository.getInstance().create(agr) != null;
    }

    public static Faculty get(Faculty agr) throws SQLException, ClassNotFoundException {
        return FacultyRepository.getInstance().getById(agr);
    }

    public static Faculty update(Faculty agr) throws ClassNotFoundException, SQLException {
        return FacultyRepository.getInstance().update(agr);
    }

    public static boolean delete(Faculty agr) throws SQLException, ClassNotFoundException {
        return FacultyRepository.getInstance().delete(agr);
    }

    public static List<Faculty> getAll() {
        return FacultyRepository.getInstance().getAll();
    }
}
