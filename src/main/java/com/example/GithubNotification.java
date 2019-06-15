package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubNotification {
    private String id;
    private boolean unread;
    private String reason;
    private String updated_at;
    private String last_read_at;
    private String url;
    private String subscription_url;

    @JsonProperty(value="subject")
    private GithubNotificationSubject githubNotificationSubject;

    @JsonProperty(value="repository")
    private GithubNotificationRepository githubNotificationRepository;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getLast_read_at() {
        return last_read_at;
    }

    public void setLast_read_at(String last_read_at) {
        this.last_read_at = last_read_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubscription_url() {
        return subscription_url;
    }

    public void setSubscription_url(String subscription_url) {
        this.subscription_url = subscription_url;
    }

    public GithubNotificationRepository getGithubNotificationRepository() {
        return githubNotificationRepository;
    }

    public void setGithubNotificationRepository(GithubNotificationRepository githubNotificationRepository) {
        this.githubNotificationRepository = githubNotificationRepository;
    }

    public GithubNotificationSubject getGithubNotificationSubject() {
        return githubNotificationSubject;
    }

    public void setGithubNotificationSubject(GithubNotificationSubject githubNotificationSubject) {
        this.githubNotificationSubject = githubNotificationSubject;
    }

    @Override
    public String toString() {
        return "GithubNotification{" +
                "id='" + id + '\'' +
                ", unread=" + unread +
                ", reason='" + reason + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", last_read_at='" + last_read_at + '\'' +
                ", url='" + url + '\'' +
                ", subscription_url='" + subscription_url + '\'' +
                ", githubNotificationSubject=" + githubNotificationSubject +
                ", githubNotificationRepository=" + githubNotificationRepository +
                '}';
    }
}

