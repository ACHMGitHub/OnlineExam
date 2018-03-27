package com.onlineExam.dao.Teacher;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.Teacher;

import java.util.List;

public interface ITeacherDao extends IBaseDao<Teacher> {

    /**
     * 持久到数据库
     * @param id
     * @param pw
     */
    void save(String id, String pw);

    //Delete

    /**
     * 主键删除
     * @param uuid
     */
    void delete(int uuid);

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
     * 按性别查找
     * @param sex
     * @return 按sex分类的Teacher list集合
     */
    List<Teacher> getBySex(int sex);

    /**
     * 按姓名查找
     * @param name
     * @return 按name模糊查找的Student list集合
     */
    List<Teacher> getByName(String name);

    /**
     * 按职称查询
     * @param title 职称
     * @return 相同职称的教师集合
     */
    List<Teacher> getByTitle(String title);

    /**
     * 分页查询所有教师
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Admin list集合
     */
    List<Teacher> findAllByPage(int startIndex, int pageSize);

    /**
     * 分页查询按班级分类的学生
     * @param title 职称
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Admin list集合
     */
    List<Teacher> findTitleByPage(String title, int startIndex, int pageSize);
}
