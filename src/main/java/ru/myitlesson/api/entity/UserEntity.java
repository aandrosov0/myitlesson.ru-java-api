package ru.myitlesson.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserEntity extends Entity {

    public enum Role {
        @SerializedName("0")
        ROOT,

        @SerializedName("1")
        ADMIN,

        @SerializedName("2")
        TEACHER,

        @SerializedName("3")
        STUDENT;

        public int getValue() {
            SerializedName serializedName;

            try {
                serializedName = getClass().getDeclaredField(this.name()).getAnnotation(SerializedName.class);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            return Integer.parseInt(serializedName.value());
        }
    }

    protected String username;

    private String password;

    protected Role role;

    protected List<Integer> courses = new ArrayList<>();

    protected List<Integer> authoredCourses = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    public List<Integer> getAuthoredCourses() {
        return authoredCourses;
    }

    public void setAuthoredCourses(List<Integer> authoredCourses) {
        this.authoredCourses = authoredCourses;
    }
}
