package com.onlineExam.service.Student;

import com.onlineExam.dao.Student.IStudentDao;
import com.onlineExam.entity.Student;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired
    IStudentDao studentDao;

    @Override
    public void delete(String id) {
        studentDao.delete(id);
    }

    @Override
    public void deleteBySex(int sex) {
        studentDao.deleteBySex(sex);
    }

    @Override
    public Student getByUuid(int uuid) {
        return studentDao.getByUuid(uuid);
    }

    @Override
    public Student getById(String id) {
        return studentDao.getById(id);
    }

    @Override
    public Student getByCard(String card) {
        return studentDao.getByCard(card);
    }

    @Override
    public Student getByPhone(String phone) {
        return studentDao.getByPhone(phone);
    }

    @Override
    public List<Student> getBySex(int sex) {
        return studentDao.getBySex(sex);
    }

    @Override
    public List<Student> findAllByPage(int startIndex, int pageSize) {
        return studentDao.findAllByPage(startIndex, pageSize);
    }

    @Override
    public List<Student> findClassByPage(String className, int startIndex, int pageSize) {
        return studentDao.findClassByPage(className, startIndex, pageSize);
    }

    @Override
    public Student login(String id, String pw) {
        Student student = getById(id);
        if(student == null)
            return null;
        if(!student.getPw().equals(pw))
            return null;
        else
            return student;
    }

    @Override
    public Boolean idUnique(String id) {
        Student student = this.getById(id);
        if(student == null)
            return true;
        else
            return false;
    }

    @Override
    public Serializable save(Student entity) {
        return studentDao.save(entity);
    }

    @Override
    public boolean allowToSave(Student entity) {

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
        if(entity.getClassName() == null)
            return  false;
        return true;
    }

    @Override
    public void update(Student entity) {
        studentDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Student entity) {
        studentDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Student entity) {
        studentDao.delete(entity);
    }

    @Override
    public Student findById(Serializable oid) {
        return studentDao.findById(oid);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return studentDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return studentDao.findRecordNumByPage(detachedCriteria);
    }
}
