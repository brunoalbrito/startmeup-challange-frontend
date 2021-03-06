package com.br.startmeup.business;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.ITarefaDAO;
import com.br.startmeup.model.Tarefa;
import com.br.startmeup.model.Usuario;
import com.br.startmeup.persistence.dao.TarefaDAO;

import java.util.List;

public class TarefaBusiness {

    private ITarefaDAO<Tarefa> tarefaDAO;

    public TarefaBusiness(){
        this.tarefaDAO = new TarefaDAO();
    }

    public List<Tarefa> buscarTarefas(Usuario usuario) {
            return tarefaDAO.findByUserId(usuario.getId());
    }

    public void criarTarefa(Tarefa tarefa) {
            tarefaDAO.create(tarefa);
    }

    public Tarefa buscarTarefa(long id) {
        return  tarefaDAO.findById(id);
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefaDAO.delete(tarefa);
    }

    public void atulizaTarefa(Tarefa tarefa) {
        tarefaDAO.update(tarefa);
    }
}
