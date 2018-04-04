package com.onlineExam.service.Grades;

import com.onlineExam.dao.Grades.IGradesDao;
import com.onlineExam.entity.Grades;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class GradesServices implements IGradesService{

    @Autowired
    IGradesDao gradesDao;

    @Override
    public Serializable save(Grades entity) {
        return gradesDao.save(entity);
    }

    @Override
    public boolean saveViaCheck(Grades entity) {
        if(!allowToSave(entity))
            return false;
        gradesDao.save(entity);
        return true;
    }

    @Override
    public boolean allowToSave(Grades entity) {
        if(entity.getGrade() == null)
            return false;
        if(entity.getStuTestPaper() == null)
            return false;
        return true;
    }

    @Override
    public void update(Grades entity) {
        gradesDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Grades entity) {
        gradesDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Grades entity) {
        gradesDao.delete(entity);
    }

    @Override
    public void delete(Serializable oid) {
        Grades grades = gradesDao.findById(oid);
        if(grades != null)
            gradesDao.delete(grades);
    }

    @Override
    public Grades findById(Serializable oid) {
        return gradesDao.findById(oid);
    }

    @Override
    public List<Grades> findAll() {
        return gradesDao.findAll();
    }

    @Override
    public List<Grades> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return gradesDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer recordNum() {
        return gradesDao.findRecordNum(DetachedCriteria.forClass(Grades.class));
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return gradesDao.findRecordNum(detachedCriteria);
    }

    @Override
    public List<Grades> findAllByPage(int startIndex, int pageSize) {
        return gradesDao.findByPage(DetachedCriteria.forClass(Grades.class), startIndex, pageSize);
    }

    @Override
    public List<Grades> findByGradeByPage(Integer minGrade, Integer maxGrade) {
        DetachedCriteria dc = DetachedCriteria.forClass(Grades.class)
                .add(Restrictions.gt("grade", minGrade))
                .add(Restrictions.le("grade", maxGrade));
        return gradesDao.findByCriteria(dc);
    }

    @Override
    public List<Grades> orderByAsc() {
        DetachedCriteria dc = DetachedCriteria.forClass(Grades.class)
                .addOrder(Order.asc("grade"));
        return gradesDao.findByCriteria(dc);
    }

    @Override
    public List<Grades> orderByDesc() {
        DetachedCriteria dc = DetachedCriteria.forClass(Grades.class)
                .addOrder(Order.desc("grade"));
        return gradesDao.findByCriteria(dc);
    }
}
