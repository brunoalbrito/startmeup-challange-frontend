package com.br.startmeup.interfaces;

import java.util.List;

public interface IEventoDAO<T> extends GenericDAO<T> {

    List<T> findByUserId(long id);
}
