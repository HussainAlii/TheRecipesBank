package com.example.therecipesbank;

public class chefs {
    private String username ="";
    private int followers = -1;

    public chefs(String username, int followers) {
        this.username = username;
        this.followers = followers;
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


}
