package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Data;
import br.edu.up.entidades.Estudio;
import br.edu.up.entidades.Tecnico;

public class EstudioPersistencia {
    public static boolean incluir(Estudio estudio) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(estudio);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean incluirData(Estudio estudio, Data data) {
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

    public static boolean excluir(Estudio estudio) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(estudio);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Estudio procurarPorNome(Estudio estudio) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Estudio where nome like :param");
        consulta.setParameter("param", estudio.getNome());
        List<Estudio> estudios = consulta.getResultList();
        if (!estudios.isEmpty()) {
            return estudios.get(0);
        }
        return null;
    }

    public static Estudio procurarPorEndereco(Estudio estudio) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Estudio where endereco = :param");
        consulta.setParameter("param", estudio.getEndereco());
        List<Estudio> estudios = consulta.getResultList();
        if (!estudios.isEmpty()) {
            return estudios.get(0);
        }
        return null;
    }

    public static List<Estudio> getEstudios(Estudio estudio) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Estudio where nome like :param");
        consulta.setParameter("param", "%" + estudio.getNome() + "%");
        List<Estudio> estudios = consulta.getResultList();
        return estudios;
    }

    public static boolean alterar(Estudio estudio) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(estudio);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static Estudio procurarPorData(Estudio estudio) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Estudio where data = :param");
        consulta.setParameter("param", estudio.getData());
        List<Estudio> datas = consulta.getResultList();
        if (!datas.isEmpty()) {
            return datas.get(0);
        }
        return null;
    }
}
