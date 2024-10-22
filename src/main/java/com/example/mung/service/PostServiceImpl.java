package com.example.mung.service;

import com.example.mung.domain.CommentDTO;
import com.example.mung.domain.PostDTO;
import com.example.mung.exception.PostNotFoundException;
import com.example.mung.mapper.CommentMapper;
import com.example.mung.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostServiceImpl(PostMapper postMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
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

    @Override
    public List<PostDTO> getPostsByCategory(String category) {
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

    // 제목으로 검색
    @Override
    public List<PostDTO> searchByTitle(String keyword) {
        return postMapper.findByTitle(keyword);
    }

    // 내용으로 검색
    @Override
    public List<PostDTO> searchByContent(String keyword) {
        return postMapper.findByContent(keyword);
    }

    // 작성자(nickname)으로 검색
    @Override
    public List<PostDTO> searchByNickname(String nickname) {
        return postMapper.findByNickname(nickname);
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

        // 댓글 목록 가져오기
        List<CommentDTO> comments = commentMapper.getCommentsByPostId(post_id);
        post.setComments(comments); // PostDTO에 댓글 목록 추가
        return post;
    }

}
