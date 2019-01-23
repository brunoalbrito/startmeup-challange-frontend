package com.br.startmeup.beans;

import com.br.startmeup.business.TarefaBusiness;
import com.br.startmeup.enums.StatusEvento;
import com.br.startmeup.helper.SessionContext;
import com.br.startmeup.model.Tarefa;
import com.br.startmeup.model.Usuario;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="tarefaBean")
@SessionScoped
public class TarefaBean {

    private DualListModel<String> tarefas;
    private List<String> tarefasSource;
    private List<String> tarefasTarget;

    private String id;

    private String nome;

    private Date dataInicio;

    private Date dataFinal;

    private StatusEvento statusEvento = StatusEvento.INICIADA;

    private int idUsuario;

    private int prioridade;

    private TarefaBusiness tarefaBusiness;

    private Usuario usuario;

    @PostConstruct
    public void init() {
        tarefaBusiness = new TarefaBusiness();
        tarefasSource = new ArrayList<>();
        tarefasTarget = new ArrayList<>();
        usuario = (Usuario) SessionContext
                .getInstance()
                .getAttribute("UsuarioLogado");
        List<Tarefa> tarefasUser = tarefaBusiness.buscarTarefas(usuario);
        for (Tarefa tarefa:
                tarefasUser) {
            tarefasSource.add(tarefa.formattedTarefa());
        }
        tarefas = new DualListModel<String>(tarefasSource, tarefasTarget);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFim) {
        this.dataFinal = dataFim;
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

    public DualListModel<String> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<String> source, List<String> target) {

        this.tarefas = new DualListModel(source, target);
    }

    public void criarTarefa(){
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(nome);
        tarefa.setDataInicio(dataInicio);
        tarefa.setDataFim(dataFinal);
        tarefa.setPrioridade(prioridade);
        tarefa.setStatusEvento(statusEvento);
        tarefa.setIdUsuario((int)usuario.getId());
        tarefasSource.add(tarefa.formattedTarefa());
        tarefaBusiness.criarTarefa(tarefa);
        setTarefas(tarefasSource, tarefasTarget);
    }

    public void buscarTarefa(){
        statusEvento = StatusEvento.valueOf("EMPROGRESSO");
    }

    public void excluirTarefa(){
        System.out.println("Caiu aqui");
    }
}
