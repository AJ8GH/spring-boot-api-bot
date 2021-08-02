package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long selectionId;
    private String runnerName;
    private Double handicap;
    private Integer sortPriority;

    public Runner() {
    }

    public Runner(String runnerName, Double handicap, Integer sortPriority) {
        this.runnerName = runnerName;
        this.handicap = handicap;
        this.sortPriority = sortPriority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public Double getHandicap() {
        return handicap;
    }

    public void setHandicap(Double handicap) {
        this.handicap = handicap;
    }

    public Integer getSortPriority() {
        return sortPriority;
    }

    public void setSortPriority(Integer sortPriority) {
        this.sortPriority = sortPriority;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "selectionId=" + selectionId +
                ", runnerName='" + runnerName + '\'' +
                ", handicap=" + handicap +
                ", sortPriority=" + sortPriority +
                '}';
    }
}
