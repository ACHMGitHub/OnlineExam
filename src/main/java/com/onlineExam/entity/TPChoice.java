package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_tpchoice", schema = "exam", catalog = "")
public class TPChoice {
    private Integer uuid;
    private Integer testPaperId;
    private Integer choiceId;
    private TestPaper testPaper;
    private Choice choice;

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
    @Column(name = "tpc_tp_id", nullable = false)
    public Integer getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(Integer testPaperId) {
        this.testPaperId = testPaperId;
    }

    @Basic
    @Column(name = "tpc_ch_id", nullable = false)
    public Integer getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPChoice tpChoice = (TPChoice) o;

        if (uuid != null ? !uuid.equals(tpChoice.uuid) : tpChoice.uuid != null) return false;
        if (testPaperId != null ? !testPaperId.equals(tpChoice.testPaperId) : tpChoice.testPaperId != null)
            return false;
        if (choiceId != null ? !choiceId.equals(tpChoice.choiceId) : tpChoice.choiceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (testPaperId != null ? testPaperId.hashCode() : 0);
        result = 31 * result + (choiceId != null ? choiceId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tpc_tp_id", referencedColumnName = "uuid", nullable = false)
    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }

    @ManyToOne
    @JoinColumn(name = "tpc_ch_id", referencedColumnName = "uuid", nullable = false)
    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
