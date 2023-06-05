package ru.myitlesson.api.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseEntity extends Entity {

    protected String title;

    protected String description;

    protected int author;

    protected List<Integer> users = new ArrayList<>();

    protected List<Integer> modules = new ArrayList<>();

    public CourseEntity() {
    }

    public CourseEntity(String title, String description, int author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public List<Integer> getModules() {
        return modules;
    }

    public void setModules(List<Integer> modules) {
        this.modules = modules;
    }
}
