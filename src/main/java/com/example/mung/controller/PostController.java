package com.example.mung.controller;

import com.example.mung.domain.PostDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시판 메인
    @GetMapping("/postMain")
    public String postMain() {
        return "postMain";
    }

    // 카테고리별 게시글 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable String category) {
        try {
            PostDTO.Category postCategory = PostDTO.Category.valueOf(category.toUpperCase());
            List<PostDTO> posts = postService.findByCategory(postCategory);
            return ResponseEntity.ok(posts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 검색 기능
    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam String keyword, @RequestParam String type) {
        try {
            List<PostDTO> posts = postService.searchPosts(keyword, type);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 게시글 작성 폼으로 이동
    @GetMapping("/new")
    public String createPostForm(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }
        model.addAttribute("postDTO", new PostDTO());  // 작성 폼에서 사용할 PostDTO를 넘김
        return "postWrite";  // 게시글 작성 폼 페이지로 이동
    }

    // 게시글 등록 및 파일 업로드 처리
    @PostMapping("/new")
    public ModelAndView createPost(@ModelAttribute PostDTO post, @RequestParam("files") MultipartFile file) {
        ModelAndView mav = new ModelAndView();

        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String uploadDir = "C:/upload/";
                String filePath = uploadDir + fileName;

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File dest = new File(filePath);
                file.transferTo(dest);

                post.setFiles(filePath);

            } catch (IOException e) {
                e.printStackTrace();
                mav.addObject("msg", "파일 저장 중 오류가 발생했습니다.");
                mav.setViewName("postWrite");
                return mav;
            }
        } else {
            post.setFiles(null);
        }

        if (postService.register(post)) {
            mav.addObject("msg", "게시글과 파일이 성공적으로 저장되었습니다.");
        } else {
            mav.addObject("msg", "게시글 저장 중 오류가 발생했습니다.");
        }
        mav.setViewName("postWrite");
        return mav;
    }

    // 게시글 상세 조회
    @GetMapping("/{post_id}")
    public String getPostById(@PathVariable int post_id, Model model) {
        PostDTO post = postService.readById(post_id);
        postService.increaseViewCount(post_id);
        model.addAttribute("post", post);
        return "postDetail";  // postDetail.html로 이동
    }

    // 게시글 수정 페이지로 이동
  /*  @GetMapping("/update/{post_id}")
    public String updatePostPage(@PathVariable int post_id, HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");
        // 로그인 여부 확인
        if (loginUser == null) {
            return "redirect:/login";  // 로그인하지 않았으면 로그인 페이지로 리다이렉트
        }

        PostDTO post = postService.readById(post_id);

        // 로그인 가져오면 하자...
        // 로그인한 사용자가 게시글 작성자인지 확인
        if (!post.getUser_id().equals(loginUser.getUserId())) {
            model.addAttribute("error", "작성자만 게시글을 수정할 수 있습니다.");
            return "postDetail";  // 게시글 상세 페이지로 리다이렉트
        }

        // 게시글 수정 페이지로 이동
        model.addAttribute("post", post);
        return "postUpdate";  // postUpdate.html로 이동
    }
   */


/*    // 게시글 삭제 처리
    @PostMapping("/delete/{post_id}")
    public String deletePost(@PathVariable int post_id, HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        // 로그인 여부 확인
        if (loginUser == null) {
            return "redirect:/login";
        }

        PostDTO post = postService.readById(post_id);

        // 작성자 여부 확인 (데이터 타입이 문자열일 경우)
        if (!post.getUser_id().equals(loginUser.getUserId())) {
            return "redirect:/postMain";  // 본인이 작성한 글이 아닌 경우 목록 페이지로 리다이렉트
        }

        // 게시글 삭제 처리
        if (postService.remove(post_id)) {
            return "redirect:/postMain";  // 성공 시 목록 페이지로 리다이렉트
        }

        return "postDelete";  // 실패 시 삭제 페이지로 돌아감
    }*/

}

