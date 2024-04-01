package com.virtualpairprogrammers.dataaccess;

import com.virtualpairprogrammers.domain.Action;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ActionDaoJpaImpl implements ActionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Action newAction) {
        em.persist(newAction);
    }

    @Override
    public List<Action> getIncompleteActions(String userId) {
        return em.createQuery("select action from Action as action where action.owningUser=:user and action.complete=false")
                .setParameter("user", userId)
                .getResultList();
    }

    @Override
    public void update(Action actionToUpdate) throws RecordNotFoundException {
        em.merge(actionToUpdate);
    }

    @Override
    public void delete(Action oldAction) throws RecordNotFoundException {
        Action action = em.find(Action.class, oldAction.getActionId());
        em.remove(action);
    }
}
