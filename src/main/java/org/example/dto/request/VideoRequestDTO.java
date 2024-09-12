package org.example.dto.request;

public class VideoRequestDTO {

    private String username;
    private String password;
    private String title;
    private String description;
    private Long views;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "VideoRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", views=" + views +
                '}';
    }
}
