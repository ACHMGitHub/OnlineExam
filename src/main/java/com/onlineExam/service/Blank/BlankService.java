package com.onlineExam.service.Blank;

import com.onlineExam.dao.Blank.IBlankDao;
import com.onlineExam.entity.Blank;
import com.onlineExam.entity.Course;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class BlankService implements IBlankService{

    @Autowired
    IBlankDao blankDao;

    @Override
    public Serializable save(Blank entity) {
        blankDao.save(entity);
        return null;
    }

    @Override
    public boolean saveViaCheck(Blank entity) {
        if(!allowToSave(entity))
            return false;
        blankDao.save(entity);
        return true;
    }

    @Override
    public boolean allowToSave(Blank entity) {
        if(entity.getQuestion() == null)
            return false;
        if(entity.getAnswer() == null)
            return false;
        if(entity.getAnalyse() == null)
            return false;
        if(entity.getTeacher() == null)
            return false;
        if(entity.getCourse() == null)
            return false;
        return true;
    }

    @Override
    public void update(Blank entity) {
        blankDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Blank entity) {
        blankDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Blank entity) {
        blankDao.delete(entity);
    }

    @Override
    public void delete(Serializable oid) {
        Blank blank = blankDao.findById(oid);
        if(blank != null)
            blankDao.delete(blank);
    }

    @Override
    public Blank findById(Serializable oid) {
        return blankDao.findById(oid);
    }

    @Override
    public List<Blank> findAll() {
        return blankDao.findAll();
    }

    @Override
    public List<Blank> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return blankDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer recordNum() {
        return blankDao.findRecordNum(DetachedCriteria.forClass(Blank.class));
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return blankDao.findRecordNum(detachedCriteria);
    }

    @Override
    public void delete(int uuid) {
        Blank blank = blankDao.getByUuid(uuid);
        if(blank == null)
            return;
        else
            delete(blank);
    }

    @Override
    public Blank getByUuid(int uuid) {
        return blankDao.getByUuid(uuid);
    }

    @Override
    public List<Blank> findByCourse(Course course) {
        DetachedCriteria dc = blankDao.getDetachedCriteria();
        dc = blankDao.findByCourse(dc, course);
        return blankDao.findByCriteria(dc);
    }

    @Override
    public Integer recordOfCourse(Course course) {
        DetachedCriteria dc = blankDao.getDetachedCriteria();
        dc = blankDao.findByCourse(dc, course);
        return blankDao.findRecordNum(dc);
    }

    @Override
    public List<Blank> allByTeacherAsc (int startIndex, int pageSize) {
        DetachedCriteria dc = blankDao.getDetachedCriteria();
        dc = blankDao.orderByTeacherAsc(dc);
        return blankDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<Blank> findAllByPage(int startIndex, int pageSize) {
        return blankDao.findAllByPage(startIndex, pageSize);
    }
}
