package com.onlineExam.service.Choice;

import com.onlineExam.dao.Choice.IChoiceDao;
import com.onlineExam.entity.Choice;
import com.onlineExam.entity.Course;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ChoiceService implements IChoiceService{

    @Autowired
    IChoiceDao choiceDao;

    @Override
    public void delete(int uuid) {
        choiceDao.delete(uuid);
    }

    @Override
    public Choice getByUuid(int uuid) {
        return choiceDao.getByUuid(uuid);
    }

    @Override
    public List<Choice> findByCourse(Course course) {
        DetachedCriteria dc = choiceDao.getDetachedCriteria();
        dc = choiceDao.findByCourse(dc, course);
        return choiceDao.findByCriteria(dc);
    }

    @Override
    public Integer recordOfCourse(Course course) {
        DetachedCriteria dc = choiceDao.getDetachedCriteria();
        dc = choiceDao.findByCourse(dc, course);
        return choiceDao.findRecordNum(dc);
    }

    @Override
    public List<Choice> allByTeacherAsc(int startIndex, int pageSize) {
        DetachedCriteria dc = choiceDao.getDetachedCriteria();
        dc = choiceDao.orderByTeacherAsc(dc);
        return choiceDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<Choice> findAllByPage(int startIndex, int pageSize) {
        return choiceDao.findAllByPage(startIndex, pageSize);
    }

    @Override
    public Serializable save(Choice entity) {
        return choiceDao.save(entity);
    }

    @Override
    public boolean saveViaCheck(Choice entity) {
        if(!allowToSave(entity))
            return false;
        save(entity);
        return true;
    }

    @Override
    public boolean allowToSave(Choice entity) {
        if(entity.getQuestion() == null)
            return false;
        if(entity.getAnswer() == null)
            return  false;
        if(entity.getChoiceA() == null)
            return false;
        if(entity.getChoiceB() == null)
            return false;
        if(entity.getChoiceC() == null)
            return false;
        if(entity.getChoiceD() == null)
            return false;
        if(entity.getAnalyse() == null)
            return false;
        if(entity.getTeacher() == null)
            return false;
        return true;
    }

    @Override
    public void update(Choice entity) {
        choiceDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Choice entity) {
        choiceDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Choice entity) {
        choiceDao.delete(entity);
    }

    @Override
    public void delete(Serializable oid) {
        Choice choice = choiceDao.findById(oid);
        if(choice != null)
            delete(choice);
    }

    @Override
    public Choice findById(Serializable oid) {
        return choiceDao.findById(oid);
    }

    @Override
    public List<Choice> findAll() {
        return choiceDao.findAll();
    }

    @Override
    public List<Choice> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return choiceDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer recordNum() {
        return choiceDao.findRecordNum(DetachedCriteria.forClass(Choice.class));
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return choiceDao.findRecordNum(detachedCriteria);
    }
}
