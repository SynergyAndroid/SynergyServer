//package com.example.sbb.answer;
//
//import com.example.sbb.community.Community;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@RequiredArgsConstructor
//@Service
//public class AnswerService {
//
//    private final AnswerRepository answerRepository;
//
//
//    public void create(Community question, String content) {
//        Answer answer = new Answer();
//        answer.setContent(content);
//        answer.setCreateDate(LocalDateTime.now());
//        answer.setQuestion(question);
//        this.answerRepository.save(answer);
//    }
//}