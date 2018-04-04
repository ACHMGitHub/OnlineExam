package com.onlineExam.service.Blank;

import com.onlineExam.entity.Blank;
import com.onlineExam.entity.Course;
import com.onlineExam.service.IBaseService;

import java.util.List;

public interface IBlankService extends IBaseService<Blank>{

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
    Blank getByUuid(int uuid);

    /**
     * 按课程查询
     * @param course 课程实体
     * @return 该课程选择题集合
     */
    List<Blank> findByCourse(Course course);

    /**
     * 该课程拥有的选择题数
     * @param course 课程
     * @return 该课程拥有的选择题数
     */
    Integer recordOfCourse(Course course);
}
