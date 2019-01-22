package com.br.startmeup.persistence.dao;

import com.br.startmeup.helper.DateHandler;
import com.br.startmeup.interfaces.ITarefaDAO;
import com.br.startmeup.model.Tarefa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO<Tarefa> {

    private String url = "http://localhost:8090/agendawebapi_war/api/tarefas";

    @Override
    public boolean create(Tarefa tarefa) {
        try {
            String urlParamters = "nome=" + tarefa.getNome().trim()
                    + "&dataInicio=" + DateHandler.parseDateToString(tarefa.getDataInicio())
                    + "&dataFim=" + DateHandler.parseDateToString(tarefa.getDataFim())
                    + "&statusTarefa=" + tarefa.getStatusEvento()
                    + "&prioridade=" + tarefa.getPrioridade()
                    + "&idUsuario=" + tarefa.getIdUsuario();

            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try {
                connection.getOutputStream().write(urlParamters.getBytes());
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK)
                    connection.disconnect();
                return true;
            } catch (Exception e) {

            }
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Tarefa findById(long id) {
        return null;
    }

    @Override
    public List<Tarefa> findAll() {
        return null;
    }

    @Override
    public boolean update(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean delete(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> findByUserId(long id) {
        List<Tarefa> eventos = new ArrayList<>();

        String parameter = "?idUsuario=" + id;

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

                    Type type = new TypeToken<List<Tarefa>>() {
                    }.getType();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        eventos = new Gson().fromJson(inputLine, type);
                    }
                    connection.disconnect();
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventos;
    }
}
