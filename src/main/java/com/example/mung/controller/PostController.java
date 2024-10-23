package com.example.mung.controller;

import com.example.mung.domain.PostDTO;
import com.example.mung.domain.UserDTO;
import com.example.mung.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public String postMain(HttpSession session,Model model) {
        List<PostDTO> posts = postService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("userInfo",session.getAttribute("userInfo"));
        System.out.println(model.getAttribute("userInfo"));
        return "postMain";
    }

    // 카테고리별 게시글 조회
    @GetMapping("/posts/category/{category}")
    public String getPostsByCategory(@PathVariable("category") String category, Model model) {
        List<PostDTO> posts = postService.getPostsByCategory(category);
        model.addAttribute("posts", posts);
        return "postMain";
    }

    // 검색 기능
    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam("keyword") String keyword,
                              @RequestParam("type") String type, Model model) {
        List<PostDTO> posts;
        if (type.equals("title")) {
            posts = postService.searchByTitle(keyword);
        } else if (type.equals("content")) {
            posts = postService.searchByContent(keyword);
        } else if (type.equals("nickname")) {
            posts = postService.searchByNickname(keyword);  // 닉네임 검색 추가
        } else {
            posts = new ArrayList<>();
        }
        model.addAttribute("posts", posts);
        return "postMain"; // 검색 결과를 postMain 페이지에 렌더링
    }

    // 게시글 작성 폼으로 이동
    @GetMapping("/new")
    public String createPostForm(HttpSession session, Model model) {
        if (session.getAttribute("userInfo") == null) {
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
                byte[] fileData = file.getBytes(); // 파일 바이트 배열로 변환
                post.setFiles(fileData); // PostDTO에 파일 데이터 저장
            } catch (IOException e) {
                e.printStackTrace();
                mav.addObject("msg", "파일 저장 중 오류가 발생했습니다.");
                mav.setViewName("postWrite");
                return mav;
            }
        } else {
            post.setFiles(null); // 파일이 없을 경우 null 설정
        }
        // 게시글 저장
        if (postService.register(post)) {
            mav.addObject("msg", "게시글과 파일이 성공적으로 저장되었습니다.");
        } else {
            mav.addObject("msg", "게시글 저장 중 오류가 발생했습니다.");
        }
        mav.setViewName("postWrite");
        return mav;
    }

    // 게시글 상세 조회
    @GetMapping("/post/{post_id}")
    public String getPostById(@PathVariable int post_id, Model model) {
        PostDTO post = postService.readById(post_id);
        postService.increaseViewCount(post_id);
        model.addAttribute("post", post);
        return "postDetail";
    }

    // 게시글 수정 페이지로 이동
    @GetMapping("/update/{post_id}")
    public String updatePostPage(@PathVariable int post_id, HttpSession session, Model model) {
        UserDTO userInfo = (UserDTO) session.getAttribute("userInfo");

        if (userInfo == null) {
            return "redirect:/login";
        }
        PostDTO post = postService.readById(post_id);

        // 작성자 확인을 nickname을 통해 처리
        if (!post.getNickname().equals(userInfo.getNickname())) {
            model.addAttribute("error", "작성자만 게시글을 수정할 수 있습니다.");
            return "postDetail";
        }

        model.addAttribute("post", post);
        return "postUpdate";
    }

    // 게시글 삭제 처리
    @PostMapping("/delete/{post_id}")
    public String deletePost(@PathVariable int post_id, HttpSession session) {
        UserDTO userInfo = (UserDTO) session.getAttribute("userInfo");

        if (userInfo == null) {
            return "redirect:/login";
        }

        PostDTO post = postService.readById(post_id);

        // 작성자 여부 확인
        if (!post.getNickname().equals(userInfo.getNickname())) {
            return "redirect:/postMain";  // 본인이 작성한 글이 아닌 경우 목록 페이지로 리다이렉트
        }

        if (postService.remove(post_id)) {
            return "redirect:/postMain";  // 성공 시 목록 페이지로 리다이렉트
        }

        return "postDelete";  // 실패 시 삭제 페이지로 돌아감
    }
}
