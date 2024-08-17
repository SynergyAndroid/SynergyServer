package com.example.sbb.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    Community findBySubject(String subject);
    Community findBySubjectAndContent(String subject, String content);
    List<Community> findBySubjectLike(String subject);

    Page<Community> findAll(Pageable pageable);
}
