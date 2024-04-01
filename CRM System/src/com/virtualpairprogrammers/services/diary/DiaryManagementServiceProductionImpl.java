package com.virtualpairprogrammers.services.diary;

import com.virtualpairprogrammers.dataaccess.ActionDao;
import com.virtualpairprogrammers.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DiaryManagementServiceProductionImpl implements DiaryManagementService {
//    @Autowired
    private ActionDao dao;

    @Autowired
    public DiaryManagementServiceProductionImpl(ActionDao dao)
    {
        this.dao = dao;
    }

    @Override
    public void recordAction(Action action)
    {
        dao.create(action);
    }

    @Override
    public List<Action> getAllIncompleteActions(String requiredUser)
    {
        return dao.getIncompleteActions(requiredUser);
    }

}
