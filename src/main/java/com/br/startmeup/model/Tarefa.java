package com.br.startmeup.model;

import com.br.startmeup.enums.StatusEvento;
import com.br.startmeup.helper.DateHandler;

import java.util.Date;

public class Tarefa {

    private long id;

    private String nome;

    private Date dataInicio;

    private Date dataFim;

    private StatusEvento statusEvento = StatusEvento.INICIADA;

    private int idUsuario;

    private int prioridade;

    public Tarefa(String nome) {
    }

    public Tarefa() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public StatusEvento getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(StatusEvento statusEvento) {
        this.statusEvento = statusEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String formattedTarefa() {
        return "Id:" + id +
                " Nome:" + nome +
                " Inicio:" + DateHandler.parseSimpleDateToString(dataInicio) +
                " Fim:" + DateHandler.parseSimpleDateToString(dataFim);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", statusEvento=" + statusEvento +
                ", idUsuario=" + idUsuario +
                ", prioridade=" + prioridade +
                '}';
    }
}
