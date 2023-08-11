package br.edu.up.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Agenda;
import br.edu.up.entidades.Estudio;
import br.edu.up.entidades.Tecnico;

public class AgendaPersistencia {
    public static boolean incluir(Agenda agenda) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(agenda);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Agenda agenda) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(agenda);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Agenda> getAgendas(Agenda agenda) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Agenda where dataAgenda like :param");
        consulta.setParameter("param", "%" + agenda.getDataAgenda() + "%");
        List<Agenda> agendas = consulta.getResultList();
        return agendas;
    }

    public static Agenda procurarPorDia(Agenda agenda) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Agenda where dataAgenda like :param");
        consulta.setParameter("param", agenda.getDataAgenda());
        List<Agenda> agendas = consulta.getResultList();
        if (!agendas.isEmpty()) {
            return agendas.get(0);
        }
        return null;
    }

    public static Agenda procurarPorData(Agenda agenda) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Agenda where dataAgenda = :param");
        consulta.setParameter("param", agenda.getDataAgenda());
        List<Agenda> datas = consulta.getResultList();
        if (!datas.isEmpty()) {
            return datas.get(0);
        }
        return null;
    }

}
