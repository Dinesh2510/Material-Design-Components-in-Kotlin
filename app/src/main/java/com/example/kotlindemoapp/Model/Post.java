package com.example.kotlindemoapp.Model;

public class Post {

    String post_id;
    String post_title;
    String post_sub_title;
    String post_content;
    String post_userid;
    String post_username;
    String post_date;
    String post_image;
    String post_link;
    String topics;
    String post_view;
    String post_like;
    String premium_flag;

    public Post(String post_id, String post_title, String post_sub_title, String post_content, String post_userid, String post_username, String post_date, String post_image, String post_link, String topics, String post_view, String post_like, String premium_flag) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_sub_title = post_sub_title;
        this.post_content = post_content;
        this.post_userid = post_userid;
        this.post_username = post_username;
        this.post_date = post_date;
        this.post_image = post_image;
        this.post_link = post_link;
        this.topics = topics;
        this.post_view = post_view;
        this.post_like = post_like;
        this.premium_flag = premium_flag;
    }

    public String getPremium_flag() {
        return premium_flag;
    }

    public void setPremium_flag(String premium_flag) {
        this.premium_flag = premium_flag;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_sub_title() {
        return post_sub_title;
    }

    public void setPost_sub_title(String post_sub_title) {
        this.post_sub_title = post_sub_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_userid() {
        return post_userid;
    }

    public void setPost_userid(String post_userid) {
        this.post_userid = post_userid;
    }

    public String getPost_username() {
        return post_username;
    }

    public void setPost_username(String post_username) {
        this.post_username = post_username;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public String getPost_link() {
        return post_link;
    }

    public void setPost_link(String post_link) {
        this.post_link = post_link;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getPost_view() {
        return post_view;
    }

    public void setPost_view(String post_view) {
        this.post_view = post_view;
    }

    public String getPost_like() {
        return post_like;
    }

    public void setPost_like(String post_like) {
        this.post_like = post_like;
    }
}
