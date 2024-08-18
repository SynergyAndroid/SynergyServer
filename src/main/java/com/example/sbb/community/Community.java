package com.example.sbb.community;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sbb.reply.Reply;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Community {
    @Id //기본키를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 1씩 증가
    private Integer id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;
}
