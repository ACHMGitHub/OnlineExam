package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_grades", schema = "exam", catalog = "")
public class Grades {
    private Integer uuid;
    private Integer grGrade;
    private StudentTP stuTestPaper;

    @Id
    @Column(name = "uuid", nullable = false)
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "gr_grade", nullable = false)
    public Integer getGrGrade() {
        return grGrade;
    }

    public void setGrGrade(Integer grGrade) {
        this.grGrade = grGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grades grades = (Grades) o;

        if (uuid != null ? !uuid.equals(grades.uuid) : grades.uuid != null) return false;
        if (grGrade != null ? !grGrade.equals(grades.grGrade) : grades.grGrade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (grGrade != null ? grGrade.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "gr_stp_id", referencedColumnName = "uuid", nullable = false)
    public StudentTP getStuTestPaper() {
        return stuTestPaper;
    }

    public void setStuTestPaper(StudentTP stuTestPaper) {
        this.stuTestPaper = stuTestPaper;
    }
}
