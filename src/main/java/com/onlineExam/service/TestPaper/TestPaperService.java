package com.onlineExam.service.TestPaper;

import com.onlineExam.dao.TestPaper.ITestPaperDao;
import com.onlineExam.entity.TestPaper;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class TestPaperService implements ITestPaperService{

    @Autowired
    ITestPaperDao testPaperDao;

    @Override
    public void delete(int uuid) {
        TestPaper testPaper = testPaperDao.getByUuid(uuid);
        if(testPaper != null)
            testPaperDao.delete(testPaper);
    }

    @Override
    public TestPaper getByUuid(int uuid) {
        return testPaperDao.getByUuid(uuid);
    }

    @Override
    public List<TestPaper> findByCourse(Integer courseId) {
        DetachedCriteria dc = testPaperDao.getDetachedCriteria();
        dc = testPaperDao.findByCourse(dc, courseId);
        return testPaperDao.findByCriteria(dc);
    }

    @Override
    public List<TestPaper> findAllByPage(int startIndex, int pageSize) {
        return testPaperDao.findByPage(DetachedCriteria.forClass(TestPaper.class), startIndex, pageSize);
    }

    @Override
    public Serializable save(TestPaper entity) {
        return testPaperDao.save(entity);
    }

    @Override
    public boolean saveViaCheck(TestPaper entity) {
        if(!allowToSave(entity))
            return false;
        save(entity);
        return true;
    }

    @Override
    public boolean allowToSave(TestPaper entity) {
        if(entity.getCourse() == null)
            return false;
        if(entity.getTotalGrade() == null)
            return false;
        if(entity.getTimeLimit() == null)
            return false;
        if(entity.getChoiceNum() == null)
            return false;
        if(entity.getBlankNum() == null)
            return false;
        return true;
    }

    @Override
    public void update(TestPaper entity) {
        if(allowToSave(entity))
            testPaperDao.update(entity);
    }

    @Override
    public void saveOrUpdate(TestPaper entity) {
        testPaperDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(TestPaper entity) {
        testPaperDao.delete(entity);
    }

    @Override
    public void delete(Serializable oid) {
        TestPaper testPaper = findById(oid);
        if(testPaper != null)
            delete(testPaper);
    }

    @Override
    public TestPaper findById(Serializable oid) {
        return testPaperDao.findById(oid);
    }

    @Override
    public List<TestPaper> findAll() {
        return testPaperDao.findAll();
    }

    @Override
    public List<TestPaper> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return testPaperDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer recordNum() {
        return testPaperDao.findRecordNum(DetachedCriteria.forClass(TestPaper.class));
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return testPaperDao.findRecordNum(detachedCriteria);
    }
}
