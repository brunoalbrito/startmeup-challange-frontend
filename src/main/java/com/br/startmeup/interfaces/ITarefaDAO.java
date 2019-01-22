package com.br.startmeup.interfaces;

import java.util.List;

public interface ITarefaDAO<T> extends GenericDAO<T> {
    List<T> findByUserId(long id);
}
