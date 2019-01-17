package com.br.startmeup.interfaces;

import java.util.List;

public interface GenericDAO <T> {

    public boolean create(T t);

    public T findById(long id);

    public List<T> findAll();

    public boolean update(T t);

    public boolean delete(T t);

}
