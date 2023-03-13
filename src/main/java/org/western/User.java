package org.western;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class User {
    enum role {
        @SerializedName("admin")
        ADMIN,
        @SerializedName("user")
        USER
    }
    private int id;
    private String username;
    private String password;

    private role roleName;

    private static int ascId = 0;

    public User() {
        this.id = ascId++;
        this.username = "";
        this.password = "";
        this.roleName = role.USER;
    }

    public User(String username, String password, String type) {
        this.id = ascId++;
        this.username = username;
        this.password = password;
        this.roleName = role.valueOf(type);
    }

    public int login(String username, String password) {
        if (Objects.equals(this.username, username) && Objects.equals(this.password, password)) {
            return 0;
        } else {
            return -1;
        }
    }
    public int signup(String username, String password) {
        if (Objects.equals(this.username, username)) {
            return -1;
        } else {
            this.username = username;
            this.password = password;
            return 0;
        }
    }

    public int getId() {
        return id;
    }
}
