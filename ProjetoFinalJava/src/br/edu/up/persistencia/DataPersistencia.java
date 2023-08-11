package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Data;

public class DataPersistencia {

    public static boolean incluir(Data data) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(data);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Data data) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(data);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Data procurarPorData(Data data) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Data where dataAgenda = :param");
        consulta.setParameter("param", data.getDataAgenda());
        List<Data> datas = consulta.getResultList();
        if (!datas.isEmpty()) {
            return datas.get(0);
        }
        return null;
    }

    public static Data procurarPorDia(Data data) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Data where dataAgenda like :param");
        consulta.setParameter("param", data.getDataAgenda());
        List<Data> datas = consulta.getResultList();
        if (!datas.isEmpty()) {
            return datas.get(0);
        }
        return null;
    }

}
