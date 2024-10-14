package com.example.mung.service;

import com.example.mung.domain.PostDTO;
import com.example.mung.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDTO> findAll() {
        return postMapper.getList();
    }

    @Override
    public PostDTO readByTitle(String titlte) {
        return postMapper.getOneByTitle(titlte);
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
}
