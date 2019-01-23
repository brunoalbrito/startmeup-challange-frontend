package com.br.startmeup.helper;

import com.br.startmeup.model.Tarefa;

import java.util.Comparator;

public class CustomComparator implements Comparator<Tarefa> {

    @Override
    public int compare(Tarefa tarefa1, Tarefa tarefa2) {
        return tarefa1.compareTo(tarefa2);
    }
}
