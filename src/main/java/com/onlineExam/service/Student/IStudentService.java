package com.onlineExam.service.Student;

import com.onlineExam.entity.Student;
import com.onlineExam.service.BaseService.IBaseService;

import java.util.List;

public interface IStudentService extends IBaseService<Student>{

    /**
     * 唯一用户名删除
     * @param id
     */
    void delete(String id);

    /**
     * 按性别删除
     * @param sex
     */
    void deleteBySex(int sex);

    //Search
    /**
     * 按uuid查找
     * @param uuid
     * @return 唯一主码的用户
     */
    Student getByUuid(int uuid);

    /**
     * 按唯一用户名查找
     * @param id
     * @return 该用户名的用户
     */
    Student getById(String id);

    /**
     * 按身份证查找
     * @param card
     * @return 该身份证号码的用户
     */
    Student getByCard(String card);

    /**
     * 按电话号码查找
     * @param phone
     * @return 持有该电话号码的用户
     */
    Student getByPhone(String phone);

    /**
     * 按性别分类
     * @param sex 1 为 male 0 为 female
     * @return 按sex分类的Admin list集合
     */
    List<Student> getBySex(int sex);

    /**
     * 分页查询所有学生
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Admin list集合
     */
    List<Student> findAllByPage(int startIndex, int pageSize);

    /**
     * 分页查询按班级分类的学生
     * @param className 班级
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Admin list集合
     */
    List<Student> findClassByPage(String className, int startIndex, int pageSize);

    /**
     * 用户登录方法
     * @param id 用户账号
     * @param pw 用户密码
     * @return 成功返回用户实例
     */
    Student login(String id, String pw);


    /**
     * 验证用户名唯一
     * @param id 待验证用户名
     * @return 唯一为true
     */
    Boolean idUnique(String id);
}
