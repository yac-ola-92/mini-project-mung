package com.example.mung;

import com.example.mung.domain.PostDTO;
import com.example.mung.mapper.PostMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class PostMapperTest {
    @Autowired
    PostMapper dao;

    @Test
    void testAllList() {dao.getList().stream().forEach(System.out::println);}

    @Test
    void testSelectByTitle() {System.out.println(dao.getOneByTitle("여기 좋아요!"));}

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

    @Test
    @Transactional
    void testInsert() {
        PostDTO dto = new PostDTO();
        dto.setUser_id(1);
        dto.setTitle("여기 괜찮아요");
        dto.setContent("나쁘지 않은거 같아요. 댕댕이랑 같이 가보세용");
        dto.setView_count(0);
        dto.setCategory(PostDTO.Category.general);
        dto.setCreated_at(LocalDateTime.now());
        dto.setPassword("2345");
        dto.setFiles(null);
        System.out.println(dao.insert(dto));
        dao.getList().stream().forEach(System.out::println);
    }

    @Test
    @Transactional
    void testDelete() {
        System.out.println(dao.delete(2));
        dao.getList().stream().forEach(System.out::println);
    }

}
