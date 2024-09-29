package com.example.sbb.reply;

import com.example.sbb.community.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import com.example.sbb.user.SiteUser;


@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;


    public void create(Community community, String content, SiteUser author) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCreateDate(LocalDateTime.now());
        reply.setCommunity(community);
        reply.setAuthor(author);
        this.replyRepository.save(reply);
    }
}