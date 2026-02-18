package br.com.gabrielwederson.financial.dao;

import br.com.gabrielwederson.financial.model.LaunchData;
import br.com.gabrielwederson.financial.util.JPAUtil;
import br.com.gabrielwederson.financial.util.ReportDAO;
import jakarta.persistence.EntityManager;
import br.com.gabrielwederson.financial.model.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LaunchDataDAO {

    public LaunchData save(LaunchData launchdata){
        System.out.println("Attention!! If you submit a task that has already been posted again, the data will not be recreated, only updated!!");
        System.out.println(" ");
        EntityManager em = new JPAUtil().getEntityManager();
        try{
            em.getTransaction().begin();
            if(launchdata.getId() == null){
                em.persist(launchdata);

            } else {
                em.merge(launchdata);
            }
            em.getTransaction().commit();
            ReportDAO.savedata(launchdata);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            em.close();
        }
        return launchdata;
    }

    public LaunchData remove(Integer id){
        EntityManager em = new JPAUtil().getEntityManager();
        LaunchData launchdata = null;
        try{
            launchdata = em.find(LaunchData.class,id);
            em.getTransaction().begin();
            em.remove(launchdata);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            em.close();
        }

        return launchdata;
    }

    public LaunchData update(Integer id, String newName, double newValue, Type newType) {
        EntityManager em = JPAUtil.getEntityManager();
        LaunchData launchData = null;

        try {
            em.getTransaction().begin();

            launchData = em.find(LaunchData.class, id);

            if (launchData == null) {
                throw new IllegalArgumentException("LaunchData ID " + id + " not found.");
            }

            // Atualiza os campos
            launchData.setName(newName);
            launchData.setValue(newValue);
            launchData.setType(newType);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro for update in LaunchData: " + e.getMessage());
        } finally {
            em.close();
        }

        return launchData;
    }

    public List<LaunchData> findAll(){
        EntityManager em = new JPAUtil().getEntityManager();
        List <LaunchData> launchdata = null;
        try{
            launchdata = em.createQuery("from LaunchData ld").getResultList();
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        } finally{
            em.close();
        }
        return launchdata;
    }

    public LaunchData findByID(Integer id){
        EntityManager em = new JPAUtil().getEntityManager();
        LaunchData launchdata = null;
        try{
            launchdata = em.find(LaunchData.class, id);
            System.out.println("Name: " + launchdata.getName() + " Value: " + launchdata.getValue() + " Type: " + launchdata.getType());
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }finally{
            em.close();
        }
        return launchdata;
    }

    public void getSum() {
        String path = "C:\\Users\\gabri\\OneDrive\\Documentos\\financial-control-system\\data";
        ReportDAO.getsum(path);

        try (BufferedReader br = new BufferedReader(new FileReader(path + "\\sum.csv"))) {
            String result = br.readLine();
            System.out.println("result: " + result);
        } catch (Exception e) {
            System.out.println("result: " + e.getMessage());
        }
    }
}

