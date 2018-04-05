package com.onlineExam.service.Choice;

import com.onlineExam.entity.Choice;
import com.onlineExam.entity.Course;
import com.onlineExam.service.IBaseService;

import java.util.List;

public interface IChoiceService extends IBaseService<Choice>{

    /**
     * 主键删除
     * @param uuid
     */
    void delete(int uuid);

    /**
     * 按uuid查找
     * @param uuid
     * @return 唯一主码的题目
     */
    Choice getByUuid(int uuid);

    /**
     * 按课程查询
     * @param course 课程实体
     * @return 该课程选择题集合
     */
    List<Choice> findByCourse(Course course);

    /**
     * 该课程拥有的选择题数
     * @param course 课程
     * @return 该课程拥有的选择题数
     */
    Integer recordOfCourse(Course course);

    /**
     * 所有记录按教师编号升序
     * @param startIndex 起始
     * @param pageSize 页面大小
     * @return 集合
     */
    List<Choice> allByTeacherAsc(int startIndex, int pageSize);
}
