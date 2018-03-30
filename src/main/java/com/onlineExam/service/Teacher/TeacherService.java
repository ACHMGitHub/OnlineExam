package com.onlineExam.service.Teacher;

import com.onlineExam.dao.Teacher.ITeacherDao;
import com.onlineExam.entity.Teacher;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class TeacherService implements ITeacherService{

    @Autowired
    ITeacherDao teacherDao;

    @Override
    public void delete(String id) {
        teacherDao.delete(id);
    }

    @Override
    public Teacher getByUuid(int uuid) {
        return teacherDao.getByUuid(uuid);
    }

    @Override
    public Teacher getById(String id) {
        return teacherDao.getById(id);
    }

    @Override
    public Teacher getByCard(String card) {
        return teacherDao.getByCard(card);
    }

    @Override
    public Teacher getByPhone(String phone) {
        return teacherDao.getByPhone(phone);
    }

    @Override
    public List<Teacher> getBySex(int sex) {
        return teacherDao.getBySex(sex);
    }

    @Override
    public List<Teacher> findAllByPage(int startIndex, int pageSize) {
        return teacherDao.findAllByPage(startIndex, pageSize);
    }

    @Override
    public List<Teacher> findTitleByPage(String title, int startIndex, int pageSize) {
        return teacherDao.findTitleByPage(title, startIndex, pageSize);
    }

    @Override
    public Teacher login(String id, String pw) {
        Teacher tch = teacherDao.getById(id);
        if(tch == null)
            return null;
        else if(!tch.getPw().equals(pw))
            return null;
        else
            return tch;
    }

    @Override
    public Boolean idUnique(String id) {
        Teacher admin = this.getById(id);
        if(admin == null)
            return true;
        else
            return false;
    }

    @Override
    public Serializable save(Teacher entity) {
        return teacherDao.save(entity);
    }

    @Override
    public boolean allowToSave(Teacher entity) {
        if(entity.getId() == null)
            return false;
        if(entity.getPw() == null)
            return false;
        if(entity.getName() == null)
            return false;
        if(entity.getSex() == null)
            return false;
        if(entity.getCard() == null)
            return false;
        if(entity.getPhone() == null)
            return false;
        if(entity.getTitle() == null)
            return false;
        return true;
    }

    @Override
    public void update(Teacher entity) {
        teacherDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Teacher entity) {
        teacherDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Teacher entity) {
        teacherDao.delete(entity);
    }

    @Override
    public Teacher findById(Serializable oid) {
        return teacherDao.findById(oid);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public List<Teacher> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return teacherDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return teacherDao.findRecordNumByPage(detachedCriteria);
    }
}
