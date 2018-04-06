package com.onlineExam.dao.StudentTP;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.StudentTP;
import com.onlineExam.entity.TestPaper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class StudentTPDao extends BaseDaoImpl<StudentTP> implements IStudentTPDao{

    @Override
    public DetachedCriteria findByGrade(DetachedCriteria detachedCriteria, int min, int max) {
        detachedCriteria.createAlias("grade", "grade");
        return detachedCriteria.add(Restrictions.between("grade.grade", min, max));
    }

    @Override
    public DetachedCriteria findByTime(DetachedCriteria detachedCriteria, Timestamp after, Timestamp before) {
        return detachedCriteria.add(Restrictions.between("stpTime", after, before));
    }

    @Override
    public DetachedCriteria findByStudent(DetachedCriteria detachedCriteria, Student student) {
        return detachedCriteria.add(Restrictions.eq("student",student));
    }

    @Override
    public DetachedCriteria findByStudentIdBlur(DetachedCriteria detachedCriteria, String studentId) {
        return detachedCriteria.add(Restrictions.like("student.id", studentId, MatchMode.ANYWHERE));
    }

    @Override
    public DetachedCriteria findByStudentClassBlur(DetachedCriteria detachedCriteria, String studentClass) {
        return detachedCriteria.add(Restrictions.like("student.className", studentClass, MatchMode.ANYWHERE));
    }

    @Override
    public DetachedCriteria findByTestPaper(DetachedCriteria detachedCriteria, TestPaper testPaper) {
        return detachedCriteria.add(Restrictions.eq("testPaper", testPaper));
    }
}
