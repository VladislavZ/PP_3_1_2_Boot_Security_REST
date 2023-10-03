package ru.kata.spring.boot_security.demo.rest_exception;

public class UserIncorrectData {
    private String info;

    public UserIncorrectData() {}

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
