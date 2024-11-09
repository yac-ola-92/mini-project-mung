package com.example.mung.service;

import com.example.mung.domain.CommentDTO;
import com.example.mung.domain.PostDTO;
import com.example.mung.exception.PostNotFoundException;
import com.example.mung.mapper.CommentMapper;
import com.example.mung.mapper.PostMapper;
import com.example.mung.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final String uploadDir = "/uploads/";

    public PostServiceImpl(PostMapper postMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<PostDTO> findAll() {
        return postMapper.getList();
    }

    @Override
    public List<PostDTO> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.getPagedPost(size, offset);
    }

    @Override
    public List<PostDTO> getPostsByCategory(String category) {
        return postMapper.getPostByCategory(category);
    }

    @Override
    @Transactional
    public boolean modify(PostDTO post) {
        return postMapper.update(post) > 0;
    }

    @Override
    @Transactional
    public boolean createPost(PostDTO postDTO) {
        return postMapper.insertPost(postDTO) > 0;
    }

    @Override
    @Transactional
    public boolean remove(int post_id) {
        return postMapper.delete(post_id) > 0;
    }

    @Override
    public boolean increaseViewCount(int post_id) {
        return postMapper.increaseViewCount(post_id) > 0;
    }

    @Override
    public List<PostDTO> searchByTitle(String keyword) {
        return postMapper.findByTitle(keyword);
    }

    @Override
    public List<PostDTO> searchByContent(String keyword) {
        return postMapper.findByContent(keyword);
    }

    @Override
    public List<PostDTO> searchByNickname(String nickname) {
        return postMapper.findByNickname(nickname);
    }

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
        List<CommentDTO> comments = commentMapper.getCommentsByPostId(post_id);
        post.setComments(comments);
        return post;
    }
}
