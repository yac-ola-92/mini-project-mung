/*
package com.example.mung.mapper;

import com.example.mung.domain.PostDTO;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @BeforeEach
    public void setUp() {
        // H2 데이터베이스를 위한 설정이나 초기화 작업을 진행할 수 있습니다.
    }

    // 모든 게시글 조회 테스트
    @Test
    public void testGetList() {
        List<PostDTO> posts = postMapper.getList();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    // 페이징 처리된 게시글 조회 테스트
    @Test
    public void testGetPagedPost() {
        List<PostDTO> posts = postMapper.getPagedPost(10, 0);
        assertNotNull(posts);
        assertTrue(posts.size() <= 10);  // 페이지 사이즈 확인
    }

    // 특정 제목으로 게시글 조회 테스트
    @Test
    public void testSearchByTitle() {
        List<PostDTO> posts = postMapper.findByTitle("Test");
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    // 카테고리별 게시글 조회 테스트
    @Test
    public void testGetPostByCategory() {
        List<PostDTO> posts = postMapper.getPostByCategory("rec");
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    // 게시글 수정 테스트
    @Test
    public void testUpdatePost() {
        PostDTO post = new PostDTO();
        post.setPost_id(1);
        post.setTitle("Updated Title");
        post.setContent("Updated Content");
        post.setCategory(PostDTO.Category.general);
        post.setPassword("1234");

        int result = postMapper.update(post);
        assertEquals(1, result); // 성공적으로 수정되었는지 확인
    }

    // 게시글 등록 테스트
    @Test
    public void testInsertPost() {
        PostDTO post = new PostDTO();
        post.setUser_id(1);
        post.setTitle("New Post");
        post.setContent("This is a new post.");
        post.setPassword("1234");

        int result = postMapper.insert(post);
        assertTrue(post.getPost_id() > 0); // AUTO_INCREMENT 확인
        assertEquals(1, result); // 성공적으로 삽입되었는지 확인
    }

    // 게시글 삭제 테스트
    @Test
    public void testDeletePost() {
        int result = postMapper.delete(1);  // ID 1번 게시글 삭제
        assertEquals(1, result); // 삭제 확인
    }

    // 조회수 증가 테스트
    @Test
    public void testIncreaseViewCount() {
        int result = postMapper.increaseViewCount(1);
        assertEquals(1, result); // 조회수 증가 성공 확인
    }

    // 게시글 검색 (타입별 검색) 테스트
    @Test
    public void testSearchPost() {
        List<PostDTO> posts = postMapper.searchPost("Test", "title");
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    // 비밀번호 확인 테스트
    @Test
    public void testFindPasswordById() {
        String password = postMapper.findPasswordById(1);
        assertNotNull(password);
        assertEquals("1234", password);
    }

    // ID로 게시글 조회 테스트
    @Test
    public void testGetOneById() {
        PostDTO post = postMapper.getOneById(1);
        assertNotNull(post);
        assertEquals(1, post.getPost_id());
    }
}
*/
