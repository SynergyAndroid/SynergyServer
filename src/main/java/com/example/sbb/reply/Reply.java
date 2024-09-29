package com.example.sbb.reply;

import java.time.LocalDateTime;

import com.example.sbb.community.Community;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.ManyToOne;
import com.example.sbb.user.SiteUser;

@Getter
@Setter
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "Text")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    @JsonBackReference // 순환 참조 방지를 위해 추가
    private Community community;

    @ManyToOne
    private SiteUser author;
}
