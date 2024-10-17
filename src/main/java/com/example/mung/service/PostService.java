package com.example.mung.service;

import com.example.mung.domain.PostDTO;

import java.util.List;

public interface PostService {

    //모든 게시글을 조회.
    List<PostDTO> findAll(); // 모든 게시글 조회

    // 페이징된 게시글 목록을 조회.
    List<PostDTO> findAll(int page, int size); // 페이징 처리된 게시글 조회

    //특정 게시글을 제목으로 조회.
    PostDTO readByTitle(String title); // 특정 게시글 제목으로 조회

    // 특정 카테고리의 게시글을 조회.
    List<PostDTO> findByCategory(PostDTO.Category category); // 카테고리별 게시글 조회

    //게시글을 수정.
    boolean modify(PostDTO post); // 게시글 수정

    //특정 게시글의 조회수를 1 증가.
    boolean increaseViewCount(int post_id); // 게시글 조회수 증가

    //특정 게시글을 삭제.
    boolean remove(int post_id); // 게시글 삭제

    //게시글을 등록.
    boolean register(PostDTO post); // 게시글 등록

    //키워드를 통해 게시글을 검색.
    List<PostDTO> searchPosts(String keyword, String type); // 게시글 검색

    // 게시글 수정, 삭제시 비밀번호 확인
    boolean checkPassword(int post_id, String password);

}



