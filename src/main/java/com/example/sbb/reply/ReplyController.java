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
import java.security.Principal;



import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.sbb.user.SiteUser;
import com.example.sbb.user.UserService;

import org.springframework.security.access.prepost.PreAuthorize; //로그인 여부 확인 후 작성 가능하게 하기

@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {
    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    private final CommunityService communityService;
    private final ReplyService replyService;
    private final UserService userService;


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public ResponseEntity<String> createReply(@PathVariable("id") Integer id,
                                              @Valid @RequestBody ReplyForm replyForm, BindingResult bindingResult, Principal principal) {
        Community community = this.communityService.getCommunity(id);
        SiteUser siteUser = this.userService.getUser(principal.getName()); //이름 불러오기

        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors occurred: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body("Validation errors occurred");
        }
        this.replyService.create(community, replyForm.getContent(), siteUser); //이름까지 추가 저장
        logger.info("Reply created successfully for community ID: {}", id);
        return ResponseEntity.ok("Reply created successfully");
    }

}