package com.example.therecipesbank;

public class post {
    private String title       = "title";
    private String post_id     = "post_id";
    private String description        = "description";
    private String img         = "img";
    private String user_id     = "user_id";


    public post(String title, String post_id, String description, String img, String user_id) {
        this.title = title;
        this.post_id = post_id;
        this.description = description;
        this.img = img;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
