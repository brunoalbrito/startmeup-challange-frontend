package com.br.startmeup.business;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.model.Evento;
import com.br.startmeup.persistence.dao.EventoDAO;

import java.util.List;

public class EventoBusiness {

    private IEventoDAO<Evento> genericDAO = new EventoDAO();

    public boolean createEvento(Evento evento){
        boolean verifica = genericDAO.create(evento);

        if(verifica)
            return true;

        return  verifica;
    }

    public List<Evento> getEventoByUsuario(long id) {
        List<Evento> eventos = genericDAO.findByUserId(id);

        return eventos;
    }
}
