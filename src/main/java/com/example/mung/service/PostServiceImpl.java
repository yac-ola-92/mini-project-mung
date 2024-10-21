package com.example.mung.service;

import com.example.mung.domain.PostDTO;
import com.example.mung.exception.PostNotFoundException;
import com.example.mung.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    // 모든 게시글 조회
    @Override
    public List<PostDTO> findAll() {
        return postMapper.getList();
    }

    // 페이징 처리된 게시글 조회
    @Override
    public List<PostDTO> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.getPagedPost(size, offset);
    }

    // 제목을 통해 게시글 조회
    @Override
    public PostDTO readByTitle(String title) {
        PostDTO post = postMapper.getOneByTitle(title);
        if (post == null) {
            throw new PostNotFoundException("게시글을 찾을 수 없습니다: " + title);
        }
        return post;
    }

    // 카테고리별 게시글 조회
    @Override
    public List<PostDTO> findByCategory(PostDTO.Category category) {
        return postMapper.getPostByCategory(category);
    }

    // 게시글 수정
    @Transactional
    @Override
    public boolean modify(PostDTO post) {
        return postMapper.update(post) > 0;
    }

    // 게시글 삭제
    @Transactional
    @Override
    public boolean remove(int post_id) {
        return postMapper.delete(post_id) > 0;
    }

    // 게시글 등록
    @Override
    public boolean register(PostDTO post) {
        // 게시글과 파일 경로 저장
        return postMapper.insert(post) > 0;
    }

    // 조회수 증가
    @Transactional
    @Override
    public boolean increaseViewCount(int post_id) {
        return postMapper.increaseViewCount(post_id) > 0;
    }

    // 게시글 검색
    @Override
    public List<PostDTO> searchPosts(String keyword, String type) {
        return postMapper.searchPost(keyword, type);
    }

    // 비밀번호 확인
    @Override
    public boolean checkPassword(int post_id, String password) {
        String storedPassword = postMapper.findPasswordById(post_id);
        return storedPassword != null && storedPassword.equals(password);
    }

    @Override
    public PostDTO readById(int post_id) {
        PostDTO post = postMapper.getOneById(post_id);
        if (post == null) {
            throw new PostNotFoundException("게시글을 찾을 수 없습니다: " + post_id);
        }
        return post;
    }
}
