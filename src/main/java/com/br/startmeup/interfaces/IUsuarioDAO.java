package com.br.startmeup.interfaces;

public interface IUsuarioDAO<T> extends GenericDAO<T> {

    public T findByEmail(String email);
}
