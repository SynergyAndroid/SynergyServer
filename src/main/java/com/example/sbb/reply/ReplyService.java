package com.example.sbb.reply;

import com.example.sbb.community.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;


    public void create(Community community, String content) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCreateDate(LocalDateTime.now());
        reply.setCommunity(community);
        this.replyRepository.save(reply);
    }
}