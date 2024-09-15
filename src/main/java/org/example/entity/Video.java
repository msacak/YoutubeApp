package org.example.entity;

import jakarta.persistence.*;
// performans açısından fetch = lazy, LazyInitiliazerException hatası alma ihtimalim olmasın diyorsan Eager fetch.
//Birinde ilgili nesneye ait referans nesneleride anında oluşturur(eager -> belleğe fazladan yük biner) diğerinde sadece
//ihtiyaç olduğuna o nesneyi oluşturur mesela getUser dersen.
// Lazy Fetch -> Daha az bellek kullanımı daha az sorgu
// İlişkili verilere hemen erişim, ek sorgu gerektirmez.
@Entity
@Table(name = "tblvideo")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String title;
    private String description;
    private Long views = 0L;


    public Video() {
    }

    public Video(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;

    }

    public Video(Long id, User user, String title, String description) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Video{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", views=" + views +
                '}';
    }
}
