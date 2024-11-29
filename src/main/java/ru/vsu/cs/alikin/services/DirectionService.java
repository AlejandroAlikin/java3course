package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.Direction;
import ru.vsu.cs.alikin.repositories.DirectionRepository;

import java.sql.SQLException;
import java.util.List;

public class DirectionService {
    public static boolean create(Direction agr) throws ClassNotFoundException {
        return DirectionRepository.getInstance().create(agr) != null;
    }

    public static Direction get(Direction agr) throws SQLException, ClassNotFoundException {
        return DirectionRepository.getInstance().getById(agr);
    }

    public static Direction update(Direction agr) throws ClassNotFoundException, SQLException {
        return DirectionRepository.getInstance().update(agr);
    }


    public static boolean delete(Direction agr) throws SQLException, ClassNotFoundException {
        return DirectionRepository.getInstance().delete(agr);
    }

    public static List<Direction> getAll() {
        return DirectionRepository.getInstance().getAll();
    }
}