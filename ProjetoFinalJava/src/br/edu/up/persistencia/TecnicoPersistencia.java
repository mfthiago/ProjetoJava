package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Data;
import br.edu.up.entidades.Tecnico;

public class TecnicoPersistencia {
    public static boolean incluir(Tecnico tecnico) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tecnico);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Tecnico tecnico) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(tecnico);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Tecnico procurarPorCPF(Tecnico tecnico) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Tecnico where cpf = :param");
        consulta.setParameter("param", tecnico.getCpf());
        List<Tecnico> tecnicos = consulta.getResultList();
        if (!tecnicos.isEmpty()) {
            return tecnicos.get(0);
        }
        return null;
    }

    public static Tecnico procurarPorID(Tecnico tecnico) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Tecnico where id = :param");
        consulta.setParameter("param", tecnico.getId());
        List<Tecnico> tecnicos = consulta.getResultList();
        if (!tecnicos.isEmpty()) {
            return tecnicos.get(0);
        }
        return null;
    }

    public static boolean alterar(Tecnico tecnico) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tecnico);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static Tecnico procurarEstudio(Tecnico tecnico) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Tecnico where estudio like :param");
        consulta.setParameter("param", tecnico.getEstudios());
        List<Tecnico> tecnicos = consulta.getResultList();
        if (!tecnicos.isEmpty()) {
            return tecnicos.get(0);
        }
        return null;
    }

    public static List<Tecnico> getTecnicos(Tecnico tecnico) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Tecnico where nome like :param");
        consulta.setParameter("param", "%" + tecnico.getNome() + "%");
        List<Tecnico> tecnicos = consulta.getResultList();
        return tecnicos;
    }

    public static Tecnico procurarPorData(Tecnico tecnico) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Tecnico where dataAgenda = :param");
        consulta.setParameter("param", tecnico.getData().getDataAgenda());
        List<Tecnico> datas = consulta.getResultList();
        if (!datas.isEmpty()) {
            return datas.get(0);
        }
        return null;
    }
}
