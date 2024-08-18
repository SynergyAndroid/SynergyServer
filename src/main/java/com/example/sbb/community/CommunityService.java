package com.example.sbb.community;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sbb.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 모든 커뮤니티 글을 페이징 처리하여 가져옴
    public Page<Community> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);  // 페이지 크기를 10으로 설정
        return this.communityRepository.findAll(pageable);
    }

    // 특정 제목의 커뮤니티 글을 가져옴
    public Community getCommunity(String title) {
        return this.communityRepository.findByTitle(title);
    }

    // ID를 통해 특정 커뮤니티 글을 가져옴
    public Community getCommunity(Integer id) {
        return this.communityRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Community post not found"));
    }

    // 새로운 커뮤니티 글을 생성
    @Transactional
    public void create(String title, String content) {
        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);
        community.setCreateDate(LocalDateTime.now()); // 현재 시간으로 설정
        this.communityRepository.save(community);
    }

    // 특정 커뮤니티 글을 업데이트 (예시: 제목과 내용을 업데이트)
    @Transactional
    public void update(Integer id, String title, String content) {
        Community community = this.getCommunity(id);
        community.setTitle(title);
        community.setContent(content);
        community.setCreateDate(LocalDateTime.now()); // 업데이트 시 현재 시간으로 갱신
        this.communityRepository.save(community);
    }

    // 특정 커뮤니티 글을 삭제
    @Transactional
    public void delete(Integer id) {
        this.communityRepository.deleteById(id);
    }
}
