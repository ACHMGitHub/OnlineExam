package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_course", schema = "exam", catalog = "")
public class Course {
    private Integer uuid;
    private String cozName;
    private Collection<TestPaper> testPapers;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "coz_name", nullable = false, length = 45)
    public String getCozName() {
        return cozName;
    }

    public void setCozName(String cozName) {
        this.cozName = cozName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (uuid != null ? !uuid.equals(course.uuid) : course.uuid != null) return false;
        if (cozName != null ? !cozName.equals(course.cozName) : course.cozName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (cozName != null ? cozName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "course")
    public Collection<TestPaper> getTestPapers() {
        return testPapers;
    }

    public void setTestPapers(Collection<TestPaper> testPapers) {
        this.testPapers = testPapers;
    }
}
