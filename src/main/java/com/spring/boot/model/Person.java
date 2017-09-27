package com.spring.boot.model;

import java.io.Serializable;

public class Person implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4322314331858996900L;

    private Long id;
    
    private String name;

    private Integer count;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
