package ru.myitlesson.api.entity;

import java.util.ArrayList;
import java.util.List;

public class ModuleEntity extends Entity {

    protected String title;
    protected String content;

    protected int course;

    protected List<Integer> lessons = new ArrayList<>();

    public ModuleEntity() {
    }

    public ModuleEntity(String title, String content, int course) {
        this.title = title;
        this.content = content;
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public List<Integer> getLessons() {
        return lessons;
    }

    public void setLessons(List<Integer> lessons) {
        this.lessons = lessons;
    }
}
