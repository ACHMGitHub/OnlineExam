package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_choice", schema = "exam", catalog = "")
public class Choice {
    private Integer uuid;
    private Integer tchId;
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;
    private String analyse;
    private Teacher teacher;
    private Collection<TPChoice> testPapers;

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
    @Column(name = "ch_tch_id", nullable = false)
    public Integer getTchId() {
        return tchId;
    }

    public void setTchId(Integer tchId) {
        this.tchId = tchId;
    }

    @Basic
    @Column(name = "ch_question", nullable = false, length = 255)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "ch_a", nullable = false, length = 255)
    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    @Basic
    @Column(name = "ch_b", nullable = false, length = 255)
    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    @Basic
    @Column(name = "ch_c", nullable = false, length = 255)
    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    @Basic
    @Column(name = "ch_d", nullable = false, length = 255)
    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    @Basic
    @Column(name = "ch_answer", nullable = false, length = 255)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "ch_analyse", nullable = false, length = 255)
    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Choice choice = (Choice) o;

        if (uuid != null ? !uuid.equals(choice.uuid) : choice.uuid != null) return false;
        if (tchId != null ? !tchId.equals(choice.tchId) : choice.tchId != null) return false;
        if (question != null ? !question.equals(choice.question) : choice.question != null) return false;
        if (A != null ? !A.equals(choice.A) : choice.A != null) return false;
        if (B != null ? !B.equals(choice.B) : choice.B != null) return false;
        if (C != null ? !C.equals(choice.C) : choice.C != null) return false;
        if (D != null ? !D.equals(choice.D) : choice.D != null) return false;
        if (answer != null ? !answer.equals(choice.answer) : choice.answer != null) return false;
        if (analyse != null ? !analyse.equals(choice.analyse) : choice.analyse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (tchId != null ? tchId.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (A != null ? A.hashCode() : 0);
        result = 31 * result + (B != null ? B.hashCode() : 0);
        result = 31 * result + (C != null ? C.hashCode() : 0);
        result = 31 * result + (D != null ? D.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (analyse != null ? analyse.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ch_tch_id", referencedColumnName = "uuid", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "choice")
    public Collection<TPChoice> getTestPapers() {
        return testPapers;
    }

    public void setTestPapers(Collection<TPChoice> testPapers) {
        this.testPapers = testPapers;
    }
}
