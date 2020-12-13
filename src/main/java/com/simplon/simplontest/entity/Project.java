package com.simplon.simplontest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;
    private String project;
    private String domain;
    private float rate;
    private boolean OneMonthBeforeExpirationEmailSended = false;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endingDate;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public boolean isOneMonthBeforeExpirationEmailSended() {
        return OneMonthBeforeExpirationEmailSended;
    }

    public void setOneMonthBeforeExpirationEmailSended(boolean oneMonthBeforeExpirationEmailSended) {
        OneMonthBeforeExpirationEmailSended = oneMonthBeforeExpirationEmailSended;
    }
}
