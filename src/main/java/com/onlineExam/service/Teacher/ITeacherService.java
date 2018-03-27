package com.onlineExam.service.Teacher;


import com.onlineExam.entity.Teacher;
import com.onlineExam.service.BaseService.IBaseService;

import java.util.List;

public interface ITeacherService extends IBaseService<Teacher>{

    /**
     * 唯一用户名删除
     * @param id
     */
    void delete(String id);

    /**
     * 按uuid查找
     * @param uuid
     * @return 唯一主码的用户
     */
    Teacher getByUuid(int uuid);

    /**
     * 按唯一用户名查找
     * @param id
     * @return 该用户名的用户
     */
    Teacher getById(String id);

    /**
     * 按身份证查找
     * @param card
     * @return 该身份证号码的用户
     */
    Teacher getByCard(String card);

    /**
     * 按电话号码查找
     * @param phone
     * @return 持有该电话号码的用户
     */
    Teacher getByPhone(String phone);

    /**
     * 按性别分类
     * @param sex 1 为 male 0 为 female
     * @return 按sex分类的Admin list集合
     */
    List<Teacher> getBySex(int sex);

    /**
     * 分页查询所有教师
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Teacher list集合
     */
    List<Teacher> findAllByPage(int startIndex, int pageSize);

    /**
     * 分页查询按职称分类的学生
     * @param title 职称
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Teacher list集合
     */
    List<Teacher> findTitleByPage(String title, int startIndex, int pageSize);

    /**
     * 用户登录方法
     * @param id 用户账号
     * @param pw 用户密码
     * @return 成功返回用户实例
     */
    Teacher login(String id, String pw);
}
