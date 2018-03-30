package com.onlineExam.service.Admin;

import com.onlineExam.dao.Admin.IAdminDao;
import com.onlineExam.entity.Admin;
import com.onlineExam.service.BaseService.BaseServiceImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class AdminService implements IAdminService{

    @Autowired
    IAdminDao adminDao;

    @Override
    public void delete(String id) {
        adminDao.delete(id);
    }

    @Override
    public void deleteBySex(int sex) {
        adminDao.deleteBySex(sex);
    }

    @Override
    public Admin getByUuid(int uuid) {
        return null;
    }

    @Override
    public Admin getById(String id) {
        return adminDao.getById(id);
    }

    @Override
    public Admin getByCard(String card) {
        return adminDao.getByCard(card);
    }

    @Override
    public Admin getByPhone(String phone) {
        return adminDao.getByPhone(phone);
    }

    @Override
    public List<Admin> getBySex(int sex) {
        return adminDao.getBySex(sex);
    }

    @Override
    public List<Admin> findAllByPage(int startIndex, int pageSize) {
        return adminDao.findAllByPage(startIndex, pageSize);
    }

    @Override
    public List<Admin> findSexByPage(int sex, int startIndex, int pageSize) {
        return adminDao.findSexByPage(sex, startIndex, pageSize);
    }

    @Override
    public Admin login(String id, String pw) {
        Admin admin = this.getById(id);
        if(admin == null)
            return null;
        if (!admin.getPw().equals(pw))
            return null;
        else
            return admin;
    }

    @Override
    public Boolean idUnique(String id) {
        Admin admin = this.getById(id);
        if(admin == null)
            return true;
        else
            return false;
    }

    @Override
    public Serializable save(Admin entity) {
        return adminDao.save(entity);
    }

    @Override
    public boolean allowToSave(Admin entity) {
        if(entity.getId() == null)
            return false;
        if(entity.getPw() == null)
            return false;
        if(entity.getName() == null)
            return false;
        if(entity.getSex() == null)
            return false;
        if(entity.getCard() == null)
            return false;
        if(entity.getPhone() == null)
            return false;
        return true;
    }

    @Override
    public void update(Admin entity) {
        adminDao.update(entity);
    }

    @Override
    public void saveOrUpdate(Admin entity) {
        adminDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Admin entity) {
        adminDao.delete(entity);
    }

    @Override
    public Admin findById(Serializable oid) {
        return adminDao.findById(oid);
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public List<Admin> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return adminDao.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return adminDao.findRecordNumByPage(detachedCriteria);
    }
}
