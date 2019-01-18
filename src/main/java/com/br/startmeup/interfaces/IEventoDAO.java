package com.br.startmeup.interfaces;

import java.util.List;

public interface IEventoDAO<T> extends GenericDAO<T> {

    public List<T> findByUserId(long id);
}
