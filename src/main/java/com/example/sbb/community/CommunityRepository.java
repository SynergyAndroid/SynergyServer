package com.example.sbb.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    Community findByTitle(String title);
    Community findByTitleAndContent(String title, String content);
    List<Community> findByTitleLike(String title);

    Page<Community> findAll(Pageable pageable);
}
