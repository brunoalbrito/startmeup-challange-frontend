package com.br.startmeup.beans;


import com.br.startmeup.business.UsuarioBusiness;
import com.br.startmeup.helper.SessionContext;
import com.br.startmeup.model.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private String nome;

    private String email;

    private String senha;

    private UsuarioBusiness business;

    public UsuarioBean(){
        business = new UsuarioBusiness();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void persistUser(){
        Usuario usuario = new Usuario();
        usuario.setNome(getNome());
        usuario.setEmail(getEmail());
        usuario.setSenha(getSenha());
        if(business.persistUsuario(usuario)) {
            try {
                SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("kanban.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logar(){
        Usuario usuario = new Usuario();
        usuario.setEmail(getEmail());
        usuario.setSenha(getSenha());
        usuario = business.loginUsuario(usuario);
        if(usuario != null){
            try {
                SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("kanban.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout(){
        SessionContext.getInstance().encerrarSessao();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
