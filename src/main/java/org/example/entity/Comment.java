package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tblcomment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Video video;


}
