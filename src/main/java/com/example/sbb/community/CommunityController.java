package com.example.sbb.community;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@RestController
public class CommunityController {
    private final CommunityService communityService;

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
//
//    // 커뮤니티 글 작성 폼 (React Native와 통신 시에는 필요 없을 수 있음)
//    @GetMapping("/community/create")
//    public ResponseEntity<String> communityCreateForm() {
//        return ResponseEntity.ok("커뮤니티 글 작성 폼");
//    }

    // 커뮤니티 글을 생성하고, 성공 메시지를 JSON으로 반환
    @PostMapping("/community/create")
    public ResponseEntity<String> communityCreate(@RequestBody CommunityForm communityForm) {
        this.communityService.create(communityForm.getTitle(), communityForm.getContent());
        return ResponseEntity.ok("커뮤니티 글이 성공적으로 생성되었습니다.");
}

}
