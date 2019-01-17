package com.br.startmeup.business;

import com.br.startmeup.model.Usuario;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.br.startmeup.persistence.dao.UsuarioDAO;

public class UsuarioBusiness {

    private UsuarioDAO usuarioDAO;

    public UsuarioBusiness(){
        this.usuarioDAO = new UsuarioDAO(SingletonConnection.getInstance().getConnection());
    }

    public boolean persistUsuario(Usuario usuario){

        if (usuario != null){
            usuarioDAO.create(usuario);
            return true;
        }

        return false;
    }

    public boolean loginUsuario(Usuario usuario){

        usuario = usuarioDAO.findByEmail(usuario.getEmail());

        if(usuario != null){
            return true;
        }
        return false;
    }
}
