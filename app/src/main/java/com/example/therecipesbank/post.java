package com.example.therecipesbank;

public class post {
    private String title       = "";
    private String post_id     = "";
    private String description = "";
    private String img         = "";
    private String username    = "";
    private int user_id        = -1;
    private int likes          = 0;

    public post(String title, String post_id, String description, String img, int user_id,String username, int likes) {
        this.title = title;
        this.post_id = post_id;
        this.description = description;
        this.img = img;
        this.user_id = user_id;
        this.likes = likes;
        this.username = username;
    }

    @Override
    public String toString() {
        return "post{" +
                "title='" + title + '\'' +
                ", post_id='" + post_id + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", user_id=" + user_id +
                ", likes=" + likes +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }
}
