package ru.vsu.cs.alikin;

import java.util.Collection;

public interface RepositoryInterface<T> {
    T save(T entity);

    T findByID(int id);

    boolean deleteById(int id);

    T update(T entity);

    Collection<T> findAll();
}
