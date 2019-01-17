package com.br.startmeup.persistence.dao;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.model.Usuario;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements GenericDAO<Usuario> {

    private Connection connection;

    private String url = "http://localhost:8090/agendawebapi_war/api/usuarios";

    public UsuarioDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Usuario usuario) {

        try {
            String urlParamters = "nome=bruno&email=bruno@bruno.com&senha=1";

            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            try{
                connection.getOutputStream().write(urlParamters.getBytes());
                int responseCode = connection.getResponseCode();
            }catch (Exception e){

            }
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public Usuario findById(long id) {

        Usuario usuario = new Usuario();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=?, senha=? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setLong(4, usuario.getId());

            if(ps.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            if(ps.executeUpdate() != 0){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}