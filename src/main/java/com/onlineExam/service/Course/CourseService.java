package com.onlineExam.service.Course;

import com.onlineExam.dao.Course.ICourseDao;
import com.onlineExam.entity.Course;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class CourseService implements ICourseService{

    @Autowired
    ICourseDao courseDao;

    @Override
    public Serializable save(Course entity) {
        return courseDao.save(entity);
    }

    @Override
    public boolean saveViaCheck(Course entity) {
        if(entity.getName() == null)
            return false;
        courseDao.save(entity);
        return true;
    }

    @Override
    public boolean allowToSave(Course entity) {
        if(entity.getName() == null)
            return false;
        else
            return true;
    }

    @Override
    public void update(Course entity) {
        courseDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Course entity) {
        courseDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Course entity) {
        courseDao.delete(entity);
    }

    @Override
    public void delete(Serializable oid) {
        Course course = courseDao.findById(oid);
        if(course != null)
            delete(course);
    }

    @Override
    public Course findById(Serializable oid) {
        return courseDao.findById(oid);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return courseDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer recordNum() {
        return courseDao.findRecordNumByPage(DetachedCriteria.forClass(Course.class));
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return courseDao.findRecordNumByPage(detachedCriteria);
    }

    @Override
    public List<Course> findAllByPage(int startIndex, int pageSize) {
        return courseDao.findByPage(DetachedCriteria.forClass(Course.class), startIndex, pageSize);
    }
}
