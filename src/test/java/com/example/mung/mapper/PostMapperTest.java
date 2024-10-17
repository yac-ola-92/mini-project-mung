package com.example.mung.mapper;

import com.example.mung.MungApplication;
import com.example.mung.domain.PostDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = MungApplication.class)
public class PostMapperTest {
    @Autowired
    private PostMapper dao;

    // 전체 게시글 조회 테스트
    @Test
    void testAllList() {dao.getList().stream().forEach(System.out::println);}

    // 특정 제목 조회 테스트
    @Test
    void testSelectByTitle() {System.out.println(dao.getOneByTitle("여기 좋아요!"));}

    //게시글 수정
    @Test
    void testUpdate() {
        PostDTO dto = new PostDTO();
        dto.setPost_id(1);
        dto.setTitle("그냥 그래요");
        dto.setContent("내돈주고 가기엔 아까운거 같아요");
        dto.setCategory(PostDTO.Category.rec);
        dto.setUpdated_at(LocalDateTime.now());
        dto.setPassword("1234");
        dto.setFiles(null);

        dao.update(dto);
        dao.getList().stream().forEach(System.out::println);
    }

    //게시글 등록
    @Test
    @Transactional
    void testInsert() {
        PostDTO dto = new PostDTO();
        dto.setUser_id(1);
        dto.setTitle("여기 괜찮아요");
        dto.setContent("나쁘지 않은거 같아요. 댕댕이랑 같이 가보세용");
        dto.setCategory(PostDTO.Category.general);
        dto.setCreated_at(LocalDateTime.now());
        dto.setPassword("2345");
        dto.setFiles(null);
        System.out.println(dao.insert(dto));
        dao.getList().stream().forEach(System.out::println);
    }

    //게시글 삭제 테스트
    @Test
    @Transactional
    void testDelete() {
        System.out.println(dao.delete(2));
        dao.getList().stream().forEach(System.out::println);
    }

    //게시글 조회수 증가 테스트
    @Test
    @Transactional
    void testGetViewCount() {
        PostDTO dto = new PostDTO();
        int post_id = 1;
        dao.increaseViewCount(post_id);
        dao.getOneByTitle("여기 괜찮아요");
        dao.getList().stream().forEach(System.out::println);
    }

    //카테고리별 검색
    @Test
    void testGetPostByCategory() {
        List<PostDTO> post = dao.getPostByCategory(PostDTO.Category.rec);
        dao.getList().stream().forEach(System.out::println);
    }

    // 제목, 내용, 작성자로 게시글 검색기능 테스트
    @Test
    void testSearchPostsByTitle() {
        List<PostDTO> post = dao.searchPost("여기", "title");
        dao.getList().stream().forEach(System.out::println);
    }

    // 게시글 ID로 비밀번호 조회 테스트
    @Test
    void testFindPasswordById() {
        String password = dao.findPasswordById(1);
        dao.getList().stream().forEach(System.out::println);
    }

}
