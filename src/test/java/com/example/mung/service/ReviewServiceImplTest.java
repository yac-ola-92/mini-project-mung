
/*
package com.example.mung.service;

import com.example.mung.domain.ReviewDTO;
import com.example.mung.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ReviewServiceImplTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    void testFindAll() {reviewService.findAll().stream().forEach(System.out::println);}

    @Test
    void testreadByRating() {
        List<ReviewDTO> reviews = reviewService.readByRating(4);
        reviews.forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        ReviewDTO review = new ReviewDTO();
        review.setReview_id(2);
        review.setRating(5);
        review.setComment("잘 놀다 왔어요");
        review.setCreated_at(LocalDateTime.now());
        reviewService.modify(review);

        ReviewDTO updatedReview = reviewService.findAll().stream()// 수정된 리뷰를 조회하여 출력
                .filter(r -> r.getReview_id() == review.getReview_id())
                // 모든 리뷰를 가져온뒤 filter를 사용하여 review_id가 수정한 리뷰의 id와 같은 리뷰만 찾기
                .findFirst()
                //findFirst()를 호출해서 해당 리뷰를 가져오고
                .orElse(null); // 리뷰 없으면 null 반환

        System.out.println(updatedReview);
    }

    @Test
    @Transactional
    void testDelete() {
        System.out.println(reviewService.remove(2));
        reviewService.findAll().stream().forEach(System.out::println);
    }

    @Test
    @Transactional
    void testRegister() {
        ReviewDTO review = new ReviewDTO();
        review.setUser_id(1);
        review.setRv_id(1);
        review.setRating(5);
        review.setComment("너무 좋아요~");
        review.setCreated_at(LocalDateTime.now());
        System.out.println(reviewService.register(review));
        reviewService.findAll().stream().forEach(System.out::println);
    }
}
*/