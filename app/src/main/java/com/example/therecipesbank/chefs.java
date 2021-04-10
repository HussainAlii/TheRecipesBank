package com.example.therecipesbank;

public class chefs {
    private String username ="";
    private int followers = -1;
    private int user_id = -1;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public chefs(String username, int followers, int user_id) {
        this.username = username;
        this.followers = followers;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "chefs{" +
                "username='" + username + '\'' +
                ", followers=" + followers +
                '}';
    }
}
