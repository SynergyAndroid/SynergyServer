//package com.example.sbb.answer;
//
//import java.time.LocalDateTime;
//
//import com.example.sbb.community.Community;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//public class Answer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(columnDefinition = "Text")
//    private String content; //답변의 내용
//
//    private LocalDateTime createDate;
//
//    @ManyToOne
//    private Community question;
//}
