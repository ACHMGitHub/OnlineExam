package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_grades", schema = "exam", catalog = "")
public class Grades {
    private Integer uuid;
    private Integer grSTPID;
    private Integer grade;
    private StudentTP stuTestPaper;

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
    @Column(name = "gr_stp_id", nullable = false)
    public Integer getGrSTPID() {
        return grSTPID;
    }

    public void setGrSTPID(Integer grSTPID) {
        this.grSTPID = grSTPID;
    }

    @Basic
    @Column(name = "gr_grade", nullable = false)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grades grades = (Grades) o;

        if (uuid != null ? !uuid.equals(grades.uuid) : grades.uuid != null) return false;
        if (grSTPID != null ? !grSTPID.equals(grades.grSTPID) : grades.grSTPID != null) return false;
        if (grade != null ? !grade.equals(grades.grade) : grades.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (grSTPID != null ? grSTPID.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
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
