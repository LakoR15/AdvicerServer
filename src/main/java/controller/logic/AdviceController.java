package controller.logic;

import model.Advice;
import utils.EMF;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class AdviceController extends EMF{

    private Advice advice = null;

    public AdviceController(Advice advice) {
        this.advice = advice;
    }
    public AdviceController(){
        super();
    }

    public Advice addToDataBase(){
        System.out.println("Выполняется addToDataBase");
        em = getEm();

        try {
            em.getTransaction().begin();
            em.persist(advice);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            em.getTransaction().rollback();
            throw new  RuntimeException("Не удалось добавить объект: " + e.getMessage());
        }finally {
            em.close();
        }
        return advice;
    }

    public List<Advice> getFromDataBase(){
        System.out.println("Выполняется getFromDataBase");

        List<Advice> advices = new ArrayList<>();

        em = getEm();
        try {
            em.getTransaction().begin();
            advices = em.createQuery("SELECT a FROM Advice a", Advice.class)
                    .getResultList();
            em.getTransaction().commit();
        }catch (PersistenceException e){
            em.getTransaction().rollback();
            throw new RuntimeException("Не удалось получить объекты: " + e.getMessage());
        }finally {
            em.close();
        }

        return advices;
    }



//    Функция изменения рейтинга/////////////////////////////////////////

//    public Advice changeRating(){
//        System.out.println("Выполняется changeRating");
//        em = getEm();
//        Advice advice = null;
//        try {
//            em.getTransaction().begin();
//
//            advice = em.find(Advice.class, this.advice.getId());
//
//            if (this.advice.getfLike()){
//                advice.setRating(true);
//            }else if(this.advice.getfDislike()){
//                advice.setRating(false);
//            }
//
//            em.flush();
//            em.getTransaction().commit();
//        }catch (PersistenceException e){
//            em.getTransaction().rollback();
//            throw new RuntimeException("Не удалось обновить объект: " + e.getMessage());
//        }finally {
//            em.close();
//        }
//        return advice;
//    }
}
