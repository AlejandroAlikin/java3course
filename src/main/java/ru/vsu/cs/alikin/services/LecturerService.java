package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.Lecturer;
import ru.vsu.cs.alikin.repositories.LecturerRepository;

import java.sql.SQLException;
import java.util.List;

public class LecturerService {
    public static boolean create(Lecturer agr) throws ClassNotFoundException {
        return LecturerRepository.getInstance().create(agr) != null;
    }

    public static Lecturer get(Lecturer agr) throws SQLException, ClassNotFoundException {
        return LecturerRepository.getInstance().getById(agr);
    }

    public static Lecturer update(Lecturer agr) throws ClassNotFoundException, SQLException {
        return LecturerRepository.getInstance().update(agr);
    }

    public static boolean delete(Lecturer agr) throws SQLException, ClassNotFoundException {
        return LecturerRepository.getInstance().delete(agr);
    }

    public static List<Lecturer> getAll() {
        return LecturerRepository.getInstance().getAll();
    }
}
