package com.example;

public class GithubNotificationSubject {
    private String title;
    private String url;
    private String latest_comment_url;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatest_comment_url() {
        return latest_comment_url;
    }

    public void setLatest_comment_url(String latest_comment_url) {
        this.latest_comment_url = latest_comment_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GithubNotificationSubject{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", latest_comment_url='" + latest_comment_url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
