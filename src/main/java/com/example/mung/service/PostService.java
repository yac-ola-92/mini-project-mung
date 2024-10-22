package com.example.mung.service;

import com.example.mung.domain.PostDTO;

import java.util.List;

public interface PostService {

    // 모든 게시글을 조회.
    List<PostDTO> findAll(); // 모든 게시글 조회

    // 페이징된 게시글 목록을 조회.
    List<PostDTO> findAll(int page, int size); // 페이징 처리된 게시글 조회

    // 게시글을 수정.
    boolean modify(PostDTO post); // 게시글 수정

    // 특정 게시글의 조회수를 1 증가.
    boolean increaseViewCount(int post_id); // 게시글 조회수 증가

    // 특정 게시글을 삭제.
    boolean remove(int post_id); // 게시글 삭제

    // 게시글을 등록.
    boolean register(PostDTO post); // 게시글 등록

    // 키워드를 통해 게시글을 검색.
    List<PostDTO> searchByTitle(String keyword); // 제목으로 검색
    List<PostDTO> searchByContent(String keyword); // 내용으로 검색
    List<PostDTO> searchByNickname(String nickname); // 닉네임으로 검색

    // 게시글 수정, 삭제 시 비밀번호 확인
    boolean checkPassword(int post_id, String password);

    // 추가: ID로 게시글 조회
    PostDTO readById(int post_id);

    // 카테고리별 게시글 조회
    List<PostDTO> getPostsByCategory(String category);
}
