package com.onlineExam.dao.StudentTP;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.StudentTP;
import com.onlineExam.entity.TestPaper;
import org.hibernate.criterion.DetachedCriteria;

import java.sql.Timestamp;

public interface IStudentTPDao extends IBaseDao<StudentTP>{

    /**
     * 按成绩查询
     * 为detachedCriteria添加与Grade的关联名为grade
     * @param detachedCriteria 已有查询条件
     * @param min 最小成绩
     * @param max 最大成绩
     * @return 加上成绩查询的查询条件
     */
    DetachedCriteria findByGrade(DetachedCriteria detachedCriteria, int min, int max);

    /**
     * 按时间查询
     * @param detachedCriteria 已有查询条件
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @return 加上时间查询的查询条件
     */
    DetachedCriteria findByTime(DetachedCriteria detachedCriteria, Timestamp after, Timestamp before);

    /**
     * 按学生查询
     * @param detachedCriteria 已有查询条件
     * @param student 学生实例
     * @return 加上学生查询的查询条件
     */
    DetachedCriteria findByStudent(DetachedCriteria detachedCriteria, Student student);

    /**
     * 按学生学号模糊查询
     * 使用前需存在createAlias("student", student)
     * @param detachedCriteria 已有查询条件
     * @param studentId 学生学号
     * @return 加上学生学号的查询条件
     */
    DetachedCriteria findByStudentIdBlur(DetachedCriteria detachedCriteria, String studentId);

    /**
     * 按学生班级模糊查询
     * 使用前需存在createAlias("student", student)
     * @param detachedCriteria 已有查询条件
     * @param studentClass 班级
     * @return 加上班级的查询条件
     */
    DetachedCriteria findByStudentClassBlur(DetachedCriteria detachedCriteria, String studentClass);

    /**
     * 按试卷查询
     * @param detachedCriteria 已有查询条件
     * @param testPaper 试卷实例
     * @return 加上试卷查询的查询条件
     */
    DetachedCriteria findByTestPaper(DetachedCriteria detachedCriteria, TestPaper testPaper);
}
