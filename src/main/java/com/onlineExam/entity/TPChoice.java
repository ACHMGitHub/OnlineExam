package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_tpchoice", schema = "exam", catalog = "")
public class TPChoice {
    private Integer uuid;
    private TestPaper testPaper;
    private Choice choice;

    @Id
    @Column(name = "uuid", nullable = false)
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPChoice tpChoice = (TPChoice) o;

        if (uuid != null ? !uuid.equals(tpChoice.uuid) : tpChoice.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
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
