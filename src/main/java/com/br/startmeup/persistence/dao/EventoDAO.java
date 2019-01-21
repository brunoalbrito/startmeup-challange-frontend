package com.br.startmeup.persistence.dao;

import com.br.startmeup.helper.DateHandler;
import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.model.Evento;
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

public class EventoDAO implements IEventoDAO<Evento> {

    private String url = "http://localhost:8090/agendawebapi_war/api/eventos";

    @Override
    public boolean create(Evento evento) {
        try {
            String urlParamters = "titulo=" + evento.getTitulo().trim()
                    + "&dataInicio=" + DateHandler.parseDateToString(evento.getDataInicio())
                    + "&dataFim=" + DateHandler.parseDateToString(evento.getDataFim())
                    +"&idUsuario="+ evento.getFkUsuario();

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
    public Evento findById(long id) {
        return null;
    }

    @Override
    public List<Evento> findAll() {
        return null;
    }

    @Override
    public boolean update(Evento evento) {
        return false;
    }

    @Override
    public boolean delete(Evento evento) {
        return false;
    }

    @Override
    public List<Evento> findByUserId(long id) {
        List<Evento> eventos = new ArrayList<>();

        String parameter = "?idUsuario="+ id;

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

                    Type type = new TypeToken<List<Evento>>() {
                    }.getType();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        eventos = new Gson().fromJson(inputLine,type);
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
