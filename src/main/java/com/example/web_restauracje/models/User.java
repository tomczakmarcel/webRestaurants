package com.example.web_restauracje.models;

public class User {
    private String Name;
    private String Surname;
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    private boolean isAdmin;


    public User() { }

    public User(String name, String surname, String email, boolean isAdmin, String userId) {
        this.Name = name;
        this.Surname = surname;
        this.email = email;
        this.isAdmin = isAdmin;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
}
