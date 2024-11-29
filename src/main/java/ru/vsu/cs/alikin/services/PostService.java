package ru.vsu.cs.alikin.services;

import ru.vsu.cs.alikin.objects.Post;
import ru.vsu.cs.alikin.repositories.PostRepository;

import java.sql.SQLException;
import java.util.List;

public class PostService {
    public static boolean create(Post agr) throws ClassNotFoundException {
        return PostRepository.getInstance().create(agr) != null;
    }

    public static Post get(Post agr) throws SQLException, ClassNotFoundException {
        return PostRepository.getInstance().getById(agr);
    }

    public static Post update(Post agr) throws ClassNotFoundException, SQLException {
        return PostRepository.getInstance().update(agr);
    }

    public static boolean delete(Post agr) throws SQLException, ClassNotFoundException {
        return PostRepository.getInstance().delete(agr);
    }

    public static List<Post> getAll() {
        return PostRepository.getInstance().getAll();
    }
}
