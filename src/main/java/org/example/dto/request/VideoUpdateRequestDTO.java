package org.example.dto.request;

public class VideoUpdateRequestDTO {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String description;

    public VideoUpdateRequestDTO() {
    }

    public VideoUpdateRequestDTO(Long id, String username, String password, String title, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
