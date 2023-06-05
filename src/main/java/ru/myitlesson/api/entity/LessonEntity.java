package ru.myitlesson.api.entity;

public class LessonEntity extends Entity {

    protected String title;

    protected String content;

    protected int module;

    public LessonEntity(String title, String content, int module) {
        this.title = title;
        this.content = content;
        this.module = module;
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

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }
}
