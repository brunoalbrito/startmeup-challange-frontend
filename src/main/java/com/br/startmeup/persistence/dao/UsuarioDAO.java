package com.br.startmeup.persistence.dao;

import com.br.startmeup.interfaces.IUsuarioDAO;
import com.br.startmeup.model.Usuario;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

public class UsuarioDAO implements IUsuarioDAO<Usuario> {


    private String url = "http://localhost:8090/agendawebapi_war/api/usuarios";


    @Override
    public boolean create(Usuario usuario) {

        try {
            String urlParamters = "nome=" + usuario.getNome().trim()
                    + "&email=" + usuario.getEmail().trim()
                    + "&senha=" + usuario.getSenha().trim();

            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try {
                connection.getOutputStream().write(urlParamters.getBytes());
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    connection.disconnect();
                    return true;
                }
                connection.disconnect();
            } catch (Exception e) {
                connection.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public Usuario findById(long id) {

        Usuario usuario = new Usuario();

        String parameter = "/"+ String.valueOf(id);

        try {
            URL url = new URL(this.url + parameter);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try {
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        return false;
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = new Usuario();

        String parameter = "?email="+ email;

        try {
            URL url = new URL(this.url + parameter);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            try {
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        usuario = new Gson().fromJson(inputLine,Usuario.class);
                    }
                    in.close();
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}