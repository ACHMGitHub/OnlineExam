package com.onlineExam.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_studenttp", schema = "exam", catalog = "")
public class StudentTP {
    private Integer uuid;
    private Integer stuId;
    private Integer testPaperId;
    private String stuAnswer;
    private Timestamp time;
    private Grades grade;
    private Student student;
    private TestPaper testPaper;

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
    @Column(name = "stp_stu_id", nullable = false)
    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "stp_tp_id", nullable = false)
    public Integer getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(Integer testPaperId) {
        this.testPaperId = testPaperId;
    }

    @Basic
    @Column(name = "stp_answer", nullable = false, length = 255)
    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    @Basic
    @Column(name = "stp_time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentTP studentTP = (StudentTP) o;

        if (uuid != null ? !uuid.equals(studentTP.uuid) : studentTP.uuid != null) return false;
        if (stuId != null ? !stuId.equals(studentTP.stuId) : studentTP.stuId != null) return false;
        if (testPaperId != null ? !testPaperId.equals(studentTP.testPaperId) : studentTP.testPaperId != null)
            return false;
        if (stuAnswer != null ? !stuAnswer.equals(studentTP.stuAnswer) : studentTP.stuAnswer != null) return false;
        if (time != null ? !time.equals(studentTP.time) : studentTP.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (stuId != null ? stuId.hashCode() : 0);
        result = 31 * result + (testPaperId != null ? testPaperId.hashCode() : 0);
        result = 31 * result + (stuAnswer != null ? stuAnswer.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "stuTestPaper")
    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    @ManyToOne
    @JoinColumn(name = "stp_stu_id", referencedColumnName = "uuid", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "stp_tp_id", referencedColumnName = "uuid", nullable = false)
    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }
}
