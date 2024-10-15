package com.example.mung.service;

import com.example.mung.domain.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> findAll(); // 모든 게시글 조회
    PostDTO readByTitle(String title); // 특정 게시글 제목으로 조회
    boolean modify(PostDTO post); // 게시글 수정
    boolean remove(int post_id); // 게시글 삭제
    boolean register(PostDTO post); // 게시글 등록
}
