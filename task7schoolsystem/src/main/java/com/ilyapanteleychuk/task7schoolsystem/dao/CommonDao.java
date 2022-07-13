package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Entity;
import java.util.List;


public interface CommonDao<T extends Entity> {

    T loadById(int id);

    List<T> loadAll();

    void deleteById(int id);

    void add(T t);

    void addAll(List<T> t);
}
