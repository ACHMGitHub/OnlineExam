package com.onlineExam.dao.Admin;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao extends BaseDaoImpl<Admin> implements IAdminDao {

    @Override
    public void save(String id, String pw) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setPw(pw);
        admin.setName("");
        admin.setSex("");
        admin.setPhone("");
        admin.setCard("");
        this.save(admin);
    }

    @Override
    public void delete(int uuid) {
        Admin admin = new Admin();
        admin.setUuid(uuid);
        delete(admin);
    }

    @Override
    public void delete(String id) {
        List admins = this.getHibernateTemplate().find("from Admin where id = :id", id);
        this.getHibernateTemplate().deleteAll(admins);
    }

    @Override
    public void deleteBySex(String sex) {
        List admins = this.getHibernateTemplate().find("from Admin where sex = :sex", sex);
        this.getHibernateTemplate().deleteAll(admins);
    }

    @Override
    public Admin getByUuid(int uuid) {
        return findById(uuid);
    }

    @Override
    public Admin getById(String id) {
        return (Admin)this.getHibernateTemplate().find("from Admin where id = :id", id).get(0);
    }

    @Override
    public Admin getByCard(String card) {
        return (Admin)this.getHibernateTemplate().find("from Admin where card = :card", card).get(0);
    }

    @Override
    public Admin getByPhone(String phone) {
        return (Admin)this.getHibernateTemplate().find("from Admin where phone = :phone", phone).get(0);
    }

    @Override
    public List<Admin> getBySex(String sex) {
        return (List<Admin>)this.getHibernateTemplate().find("from Admin where sex = :sex", sex);
    }

}
