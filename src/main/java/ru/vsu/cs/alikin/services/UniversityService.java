package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.University;
import ru.vsu.cs.alikin.repositories.UniversityRepository;

import java.sql.SQLException;
import java.util.List;

public class UniversityService {
    public static boolean create(University agr) throws ClassNotFoundException {
        return UniversityRepository.getInstance().create(agr) != null;
    }

    public static University get(University agr) throws SQLException, ClassNotFoundException {
        return UniversityRepository.getInstance().getById(agr);
    }

    public static University update(University agr) throws ClassNotFoundException, SQLException {
        return UniversityRepository.getInstance().update(agr);
    }

    public static boolean delete(University agr) throws SQLException, ClassNotFoundException {
        return UniversityRepository.getInstance().delete(agr);
    }

    public static List<University> getAll() { return UniversityRepository.getInstance().getAll(); }
}
