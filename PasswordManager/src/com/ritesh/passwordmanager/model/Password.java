package com.ritesh.passwordmanager.model;

public class Password {

    private int id;
    private String website;
    private String username;
    private String password;
    private String createdAt;

    // Default Constructor
    public Password() {

    }

    // Parameterized Constructor
    public Password(int id, String website, String username, String password, String createdAt) {

        this.id = id;
        this.website = website;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Getter Methods

    public int getId() {
        return id;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}