package com.onlineExam.dao.Admin;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Admin;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
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
        admin.setSex(1);
        admin.setPhone("");
        admin.setCard("");
        this.save(admin);
    }

    @Override
    public void delete(int uuid) {
        Admin admin = findById(uuid);
        if(admin != null)
            delete(admin);
    }

    @Override
    public void delete(String id) {
        Admin admin = getById(id);
        if(admin != null)
            delete(admin);
    }

    @Override
    public void deleteBySex(int sex) {
        List admins = this.getHibernateTemplate().find("from Admin where sex = ?", sex);
        if(admins.size() > 0)
            this.getHibernateTemplate().deleteAll(admins);
    }

    @Override
    public Admin getByUuid(int uuid) {
        return findById(uuid);
    }

    @Override
    public Admin getById(String id) {
        List admins = this.getHibernateTemplate().find("from Admin where id = ?", id);
        if(admins.size() > 0)
            return (Admin)admins.get(0);
        else
            return null;
    }

    @Override
    public Admin getByCard(String card) {
        List admins = this.getHibernateTemplate().find("from Admin where card = ?", card);
        if(admins.size() > 0)
            return (Admin) admins.get(0);
        else
            return null;
    }

    @Override
    public Admin getByPhone(String phone) {
        List admins = this.getHibernateTemplate().find("from Admin where phone = ?", phone);
        if(admins.size() > 0)
            return (Admin) admins.get(0);
        else
            return null;
    }

    @Override
    public List<Admin> getBySex(int sex) {
        List admins = this.getHibernateTemplate().find("from Admin where sex = ?", sex);
        if(admins.size() > 0)
            return (List<Admin>) admins;
        else
            return null;
    }

    @Override
    public List<Admin> findAllByPage(int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
        return this.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<Admin> findSexByPage(int sex, int startIndex, int pageSize) {
//        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class).add(Restrictions.eq("sex",sex));
        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class).add( Property.forName("sex").eq(sex));
        return this.findByPage(dc, startIndex, pageSize);
    }


}
