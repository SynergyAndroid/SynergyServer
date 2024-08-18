package com.example.sbb.reply;

import com.example.sbb.community.Community;
import com.example.sbb.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;


@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final CommunityService communityService;
    private final ReplyService replyService;

    @PostMapping("/create/{id}")
    public ResponseEntity<String> createReply(@PathVariable("id") Integer id,
                                              @Valid @RequestBody ReplyForm replyForm, BindingResult bindingResult) {
        Community community = this.communityService.getCommunity(id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors occurred");
        }
        this.replyService.create(community, replyForm.getContent());
        return ResponseEntity.ok("Reply created successfully");
    }

}