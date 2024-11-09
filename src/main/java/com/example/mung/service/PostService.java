package com.example.mung.service;

import com.example.mung.domain.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    List<PostDTO> findAll(); // 모든 게시글 조회

    List<PostDTO> findAll(int page, int size); // 페이징 처리된 게시글 조회

    boolean modify(PostDTO post); // 게시글 수정

    boolean increaseViewCount(int post_id); // 게시글 조회수 증가

    boolean remove(int post_id); // 게시글 삭제
    boolean createPost(PostDTO postDTO);


    List<PostDTO> searchByTitle(String keyword); // 제목으로 검색

    List<PostDTO> searchByContent(String keyword); // 내용으로 검색

    List<PostDTO> searchByNickname(String nickname); // 닉네임으로 검색

    boolean checkPassword(int post_id, String password); // 비밀번호 확인

    PostDTO readById(int post_id); // ID로 게시글 조회

    List<PostDTO> getPostsByCategory(String category); // 카테고리별 게시글 조회
}
