package com.example.sbb.reply;

import com.example.sbb.community.Community;
import com.example.sbb.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {
    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    private final CommunityService communityService;
    private final ReplyService replyService;

    @PostMapping("/create/{id}")
    public ResponseEntity<String> createReply(@PathVariable("id") Integer id,
                                              @Valid @RequestBody ReplyForm replyForm, BindingResult bindingResult) {
        Community community = this.communityService.getCommunity(id);
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors occurred: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body("Validation errors occurred");
        }
        this.replyService.create(community, replyForm.getContent());
        logger.info("Reply created successfully for community ID: {}", id);
        return ResponseEntity.ok("Reply created successfully");
    }

}