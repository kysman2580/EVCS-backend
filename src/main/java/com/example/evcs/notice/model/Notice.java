package com.example.evcs.notice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDate date;

    public Notice() {}

    public Notice(String title, String author, String content, LocalDate date) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
    }
 
} 
