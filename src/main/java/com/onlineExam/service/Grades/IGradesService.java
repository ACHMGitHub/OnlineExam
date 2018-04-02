package com.onlineExam.service.Grades;

import com.onlineExam.entity.Grades;
import com.onlineExam.service.IBaseService;

import java.util.List;

public interface IGradesService extends IBaseService<Grades>{

    /**
     * 成绩分页查询
     * @param minGrade 最小分数
     * @param maxGrade 最大分数
     * @return
     */
    List<Grades> findByGradeByPage(Integer minGrade, Integer maxGrade);

    /**
     * 升序排列分数
     * @return 集合
     */
    List<Grades> orderByAsc();

    /**
     * 降序排列分数
     * @return 集合
     */
    List<Grades> orderByDesc();

}
