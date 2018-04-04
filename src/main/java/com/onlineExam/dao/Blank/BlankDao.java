package com.onlineExam.dao.Blank;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Blank;
import com.onlineExam.entity.Course;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlankDao extends BaseDaoImpl<Blank> implements IBlankDao{

    @Override
    public void delete(int uuid) {
        Blank blank = getByUuid(uuid);
        if(blank != null)
            delete(blank);
    }

    @Override
    public Blank getByUuid(int uuid) {
        return this.findById(uuid);
    }

    @Override
    public List<Blank> findAllByPage(int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Blank.class);
        return this.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public DetachedCriteria findByCourse(DetachedCriteria detachedCriteria, Course course) {
//        detachedCriteria.createAlias("course", "course");
        return detachedCriteria.add(Restrictions.eq("course", course));
    }
}
