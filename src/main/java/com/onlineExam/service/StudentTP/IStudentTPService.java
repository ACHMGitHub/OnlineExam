package com.onlineExam.service.StudentTP;

import com.onlineExam.entity.Student;
import com.onlineExam.entity.StudentTP;
import com.onlineExam.entity.TestPaper;
import com.onlineExam.service.IBaseService;

import java.sql.Timestamp;
import java.util.List;

public interface IStudentTPService extends IBaseService<StudentTP>{

    /**
     * 按成绩查询
     * @param min 最小成绩
     * @param max 最大成绩
     * @return 成绩查询的集合
     */
    List<StudentTP> findByGrade(int min, int max);

    /**
     * 按成绩分页查询
     * @param min 最小成绩
     * @param max 最大成绩
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return 成绩查询的集合
     */
    List<StudentTP> findByGrade(int min, int max, int startIndex, int pageSize);
    /**
     * 按时间查询
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @return 时间查询的集合
     */
    List<StudentTP> findByTime(Timestamp after, Timestamp before);

    /**
     * 按时间查询
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return 时间查询的集合
     */
    List<StudentTP> findByTime(Timestamp after, Timestamp before, int startIndex, int pageSize);

    /**
     * 按学生查询
     * @param student 学生实例
     * @return 学生查询的集合
     */
    List<StudentTP> findByStudent(Student student);

    /**
     * 按学生分页查询
     * @param student 学生实例
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return 学生查询的集合
     */
    List<StudentTP> findByStudent(Student student, int startIndex, int pageSize);

    /**
     * 按时间和学生查询
     * @param student 学生实例
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @return 按时间和学生分页查询的集合
     */
    List<StudentTP> findByTimeStudent(Student student, Timestamp after, Timestamp before);

    /**
     * 按时间和学生查询的总数
     * @param student 学生实例
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @return 按时间和学生查询的总数
     */
    Integer recordOfTimeStudent(Student student, Timestamp after, Timestamp before);

    /**
     * 按时间和学生分页查询
     * @param student 学生实例
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return 按时间和学生分页查询的集合
     */
    List<StudentTP> findByTimeStudent(Student student, Timestamp after, Timestamp before, int startIndex, int pageSize);

    /**
     * 按时间、成绩和学生的总数
     * @param student 学生实例
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @param min 最小成绩
     * @param max 最大成绩
     * @return 按时间、成绩和学生分页查询的总数
     */
    Integer recordOfTimeGradeStudent(Student student, Timestamp after, Timestamp before, int min, int max);
    /**
     * 按时间、成绩和学生分页查询
     * @param student 学生实例
     * @param after 在此时间之后
     * @param before 在此时间之前
     * @param min 最小成绩
     * @param max 最大成绩
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return 按时间、成绩和学生分页查询的集合
     */
    List<StudentTP> findByTimeGradeStudent(Student student, Timestamp after, Timestamp before, int min, int max, int startIndex, int pageSize);

    /**
     * 按时间、成绩和学生学号,班级的总数
     * @param stuId 学号
     * @param className 班级
     * @param after  在此时间之后
     * @param before 在此时间之前
     * @param min 最小成绩
     * @param max 最大成绩
     * @return  按时间、成绩和学生学号,班级的总数
     */
    Integer recordOfTimeGradeStudent(String stuId, String className, Timestamp after, Timestamp before, int min, int max);

    /**
     * 按时间、成绩和学生学号,班级的集合
     * @param stuId 学号
     * @param className 班级
     * @param after  在此时间之后
     * @param before 在此时间之前
     * @param min 最小成绩
     * @param max 最大成绩
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return  按时间、成绩和学生学号,班级的集合
     */
    List<StudentTP> findByTimeGradeStudent(String stuId, String className, Timestamp after, Timestamp before,
                                           int min, int max, int startIndex, int pageSize);

    /**
     * 按试卷查询
     * @param testPaper 试卷实例
     * @return 试卷查询的集合
     */
    List<StudentTP> findByTestPaper(TestPaper testPaper);

    /**
     * 按试卷分页查询
     * @param testPaper 试卷实例
     * @param startIndex 起始序号
     * @param pageSize 页面大小
     * @return 试卷查询的集合
     */
    List<StudentTP> findByTestPaper(TestPaper testPaper, int startIndex, int pageSize);
}
