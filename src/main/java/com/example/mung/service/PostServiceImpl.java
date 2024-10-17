package com.example.mung.service;

import com.example.mung.domain.PostDTO;
import com.example.mung.exception.PostNotFoundException;
import com.example.mung.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
// 전체 게시글 조회
    @Override
    public List<PostDTO> findAll() {
        return postMapper.getList();
    }
// 페이징 처리된 게시글 목록 조회
    @Override
    public List<PostDTO> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.getPagedPost(size, offset);
    }

    //특정 제목의 게시글 조회
    @Override
    public PostDTO readByTitle(String title) {
        PostDTO post = postMapper.getOneByTitle(title);
        if (post == null) {
            throw new PostNotFoundException("게시글을 찾을 수 없습니다: " + title);
        }
        return post;
    }
// 특정 게시글에 속한 게시글 목록 조회
    @Override
    public List<PostDTO> findByCategory(PostDTO.Category category) {
        return postMapper.getPostByCategory(category); // 수정된 부분
    }

    @Override
    public boolean modify(PostDTO post) {
        return postMapper.update(post);
    }

    @Override
    public boolean remove(int post_id) {
        return postMapper.delete(post_id);
    }

    @Override
    public boolean register(PostDTO post) {
        return postMapper.insert(post);
    }

    // 특정 게시글의 조회수 1증가
    @Override
    public boolean increaseViewCount(int post_id) {
        return postMapper.increaseViewCount(post_id); // 조회수 증가 메서드로 변경
    }

    // 키워드를 통해 게시글 검색(제목, 내용, 작성자)
    @Override
    public List<PostDTO> searchPosts(String keyword, String type) {
        return postMapper.searchPost(keyword, type);
    }

    // 비밀번호 확인 로직
    @Override
    public  boolean checkPassword(int post_id, String password) {
        String checkPw = postMapper.findPasswordById(post_id);
        return checkPw != null && checkPw.equals(password);
    }
}
