package com.onlineExam.dao.Choice;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Choice;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDao extends BaseDaoImpl<Choice> implements IChoiceDao{

    @Override
    public void delete(int uuid) {
        Choice choice = getByUuid(uuid);
        if(choice != null)
            delete(choice);
    }

    @Override
    public Choice getByUuid(int uuid) {
        return this.findById(uuid);
    }

    @Override
    public List<Choice> findAllByPage(int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Choice.class);
        return this.findByPage(dc, startIndex, pageSize);
    }
}
