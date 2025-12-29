package com.fintech.webhook_api.model;



public class Student {
    private Long id;
    private String name;
    private String status;   // this field updates


    // REQUIRED: No-args constructor (for Jackson)
    public Student() {
    }

    // REQUIRED: All-args constructor (for your SSE logic)
    public Student(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
