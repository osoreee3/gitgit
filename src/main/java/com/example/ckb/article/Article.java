package com.example.ckb.article;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String subject;//제목

    @Column(columnDefinition = "TEXT")
    private String content;//내용

    private LocalDateTime createDate;
}
