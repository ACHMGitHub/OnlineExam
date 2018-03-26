package com.onlineExam.dao.Admin;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.Admin;
import java.util.List;

public interface IAdminDao extends IBaseDao<Admin> {

    //Add

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
     * 按性别删除
     * @param sex
     */
    void deleteBySex(String sex);

    //Search

    /**
     * 按uuid查找
     * @param uuid
     * @return 唯一主码的用户
     */
    Admin getByUuid(int uuid);

    /**
     * 按唯一用户名查找
     * @param id
     * @return 该用户名的用户
     */
    Admin getById(String id);

    /**
     * 按身份证查找
     * @param card
     * @return 该身份证号码的用户
     */
    Admin getByCard(String card);

    /**
     * 按电话号码查找
     * @param phone
     * @return 持有该电话号码的用户
     */
    Admin getByPhone(String phone);

    /**
     * 按性别分类
     * @param sex
     * @return 按sex分类的Admin list集合
     */
    List<Admin> getBySex(String sex);

    /**
     * 分页查询所有管理员
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Admin list集合
     */
    List<Admin> findAllByPage(int startIndex, int pageSize);

    /**
     * 分页查询按性别分类的管理员
     * @param sex 性别
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的Admin list集合
     */
    List<Admin> findSexByPage(String sex, int startIndex, int pageSize);

}
