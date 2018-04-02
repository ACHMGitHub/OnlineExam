package com.onlineExam.service.StudentTP;

import com.onlineExam.dao.StudentTP.IStudentTPDao;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.StudentTP;
import com.onlineExam.entity.TestPaper;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Service
public class StudentTPService implements IStudentTPService{

    @Autowired
    IStudentTPDao studentTPDao;

    @Override
    public Serializable save(StudentTP entity) {
        return studentTPDao.save(entity);
    }

    @Override
    public boolean saveViaCheck(StudentTP entity) {
        if(!allowToSave(entity))
            return false;
        studentTPDao.save(entity);
        return true;
    }

    @Override
    public boolean allowToSave(StudentTP entity) {
        if(entity.getTestPaper() == null)
            return false;
        if(entity.getStudent() == null)
            return false;
        if(entity.getGrade() == null)
            return false;
        if(entity.getStuAnswer() == null)
            return false;
        if(entity.getStpTime() == null)
            return false;
        return true;
    }

    @Override
    public void update(StudentTP entity) {
        studentTPDao.save(entity);
    }

    @Override
    public void saveOrUpdate(StudentTP entity) {
        studentTPDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(StudentTP entity) {
        studentTPDao.delete(entity);
    }

    @Override
    public void delete(Serializable oid) {
        StudentTP stp = studentTPDao.findById(oid);
        if(stp == null)
            return;
        delete(stp);
    }

    @Override
    public StudentTP findById(Serializable oid) {
        return studentTPDao.findById(oid);
    }

    @Override
    public List<StudentTP> findAll() {
        return studentTPDao.findAll();
    }

    @Override
    public List<StudentTP> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return studentTPDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer recordNum() {
        return studentTPDao.findRecordNumByPage(DetachedCriteria.forClass(StudentTP.class));
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return studentTPDao.findRecordNumByPage(detachedCriteria);
    }

    @Override
    public List<StudentTP> findAllByPage(int startIndex, int pageSize) {
        return studentTPDao.findByPage(DetachedCriteria.forClass(StudentTP.class), startIndex, pageSize);
    }

    @Override
    public List<StudentTP> findByGrade(int min, int max) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByGrade(dc, min, max);
        return studentTPDao.findByCriteria(dc);
    }

    @Override
    public List<StudentTP> findByGrade(int min, int max, int startIndex, int pageSize) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByGrade(dc, min, max);
        return studentTPDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<StudentTP> findByTime(Timestamp after, Timestamp before) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByTime(dc, after, before);
        return studentTPDao.findByCriteria(dc);
    }

    @Override
    public List<StudentTP> findByTime(Timestamp after, Timestamp before, int startIndex, int pageSize) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByTime(dc, after, before);
        return studentTPDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<StudentTP> findByStudent(Student student) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        return studentTPDao.findByCriteria(dc);
    }

    @Override
    public List<StudentTP> findByStudent(Student student, int startIndex, int pageSize) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        return studentTPDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<StudentTP> findByTimeStudent(Student student, Timestamp after, Timestamp before) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        dc = studentTPDao.findByTime(dc, after, before);
        return studentTPDao.findByPage(dc, 0, 100);
    }

    @Override
    public Integer recordOfTimeStudent(Student student, Timestamp after, Timestamp before) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        dc = studentTPDao.findByTime(dc, after, before);
        return studentTPDao.findRecordNumByPage(dc);
    }

    @Override
    public List<StudentTP> findByTimeStudent(Student student, Timestamp after, Timestamp before, int startIndex, int pageSize) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        dc = studentTPDao.findByTime(dc, after, before);
        return studentTPDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public Integer recordOfTimeGradeStudent(Student student, Timestamp after, Timestamp before, int min, int max) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        dc = studentTPDao.findByTime(dc, after, before);
        dc = studentTPDao.findByGrade(dc, min, max);
        return studentTPDao.findRecordNumByPage(dc);
    }

    @Override
    public List<StudentTP> findByTimeGradeStudent(Student student, Timestamp after, Timestamp before, int min, int max, int startIndex, int pageSize) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByStudent(dc, student);
        dc = studentTPDao.findByTime(dc, after, before);
        dc = studentTPDao.findByGrade(dc, min, max);
        return studentTPDao.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<StudentTP> findByTestPaper(TestPaper testPaper) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByTestPaper(dc, testPaper);
        return studentTPDao.findByCriteria(dc);
    }

    @Override
    public List<StudentTP> findByTestPaper(TestPaper testPaper, int startIndex, int pageSize) {
        DetachedCriteria dc = studentTPDao.getDetachedCriteria();
        dc = studentTPDao.findByTestPaper(dc, testPaper);
        return studentTPDao.findByPage(dc, startIndex, pageSize);
    }
}
