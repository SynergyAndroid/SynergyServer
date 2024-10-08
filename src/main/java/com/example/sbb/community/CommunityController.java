package com.example.sbb.community;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.security.Principal;
import com.example.sbb.user.SiteUser;
import com.example.sbb.user.UserService;

import org.springframework.security.access.prepost.PreAuthorize; //로그인 여부 확인 후 작성 가능하게 설정

@RequiredArgsConstructor
@RestController
public class CommunityController {
    private final CommunityService communityService;
    private final UserService userService;


    // 커뮤니티 글 목록을 JSON으로 반환
    @GetMapping("/community/list")
    public ResponseEntity<Page<Community>> list(@RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Community> paging = this.communityService.getList(page);
        return ResponseEntity.ok(paging);
    }

    // 특정 커뮤니티 글의 상세 정보를 JSON으로 반환
    @GetMapping(value = "/community/detail/{id}")
    public ResponseEntity<Community> detail(@PathVariable("id") Integer id) {
        Community community = this.communityService.getCommunity(id);
        return ResponseEntity.ok(community);
    }

    @PreAuthorize("isAuthenticated()")
    // 커뮤니티 글을 생성하고, 성공 메시지를 JSON으로 반환
    @PostMapping("/community/create")
    public ResponseEntity<String> communityCreate(@RequestBody CommunityForm communityForm, Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());

        this.communityService.create(communityForm.getTitle(), communityForm.getContent(), siteUser);
        return ResponseEntity.ok("커뮤니티 글이 성공적으로 생성되었습니다.");
}

}
