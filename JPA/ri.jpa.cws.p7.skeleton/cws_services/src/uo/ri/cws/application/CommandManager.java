package uo.ri.cws.application;

import javax.persistence.EntityManager;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.application.util.command.CommandExecutor;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class CommandManager implements CommandExecutor {

    @Override
    public <T> T execute(Command<T> cmd) throws BusinessException {
        EntityManager entityManager = Jpa.createEntityManager();
        T result = null;
        try{
            entityManager.getTransaction().begin();

            result = cmd.execute();

            entityManager.getTransaction().commit();
        }catch (BusinessException businessException){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw businessException;
        }
        finally {
            entityManager.close();
        }
        return result;
    }
}
