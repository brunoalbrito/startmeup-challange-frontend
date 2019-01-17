package com.br.startmeup.business;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.model.Evento;
import com.br.startmeup.persistence.dao.EventoDAO;

public class EventoBusiness {

    private GenericDAO<Evento> genericDAO = new EventoDAO();

    public boolean createEvento(Evento evento){
        boolean verifica = genericDAO.create(evento);

        if(verifica)
            return true;

        return  verifica;
    }
}
