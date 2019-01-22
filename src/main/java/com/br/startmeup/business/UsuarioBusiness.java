package com.br.startmeup.business;

import com.br.startmeup.model.Usuario;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.br.startmeup.persistence.dao.UsuarioDAO;

public class UsuarioBusiness {

    private UsuarioDAO usuarioDAO;

    public UsuarioBusiness(){
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean persistUsuario(Usuario usuario){

        if (usuario != null){
            usuarioDAO.create(usuario);
            return true;
        }

        return false;
    }

    public Usuario loginUsuario(Usuario usuario){

        String password = usuario.getSenha();
        usuario = usuarioDAO.findByEmail(usuario.getEmail());

        if(usuario.getSenha() != null && usuario.getSenha().equals(password)){
            return usuario;
        }
        return null;
    }
}
