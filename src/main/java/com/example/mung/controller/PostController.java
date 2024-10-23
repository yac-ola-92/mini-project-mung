package com.example.mung.controller;

import com.example.mung.domain.PostDTO;
import com.example.mung.domain.UserVO;
import com.example.mung.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    private UserVO getLoginUser(HttpSession session) {
        return (UserVO) session.getAttribute("userInfo");
    }

    // 게시판 메인
    @GetMapping("/postMain")
    public String postMain(HttpSession session, Model model) {
        UserVO userInfo = getLoginUser(session);
        List<PostDTO> posts = postService.findAll();
        model.addAttribute("posts", posts);
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);  // 로그인된 사용자 정보 추가
        }
        return "postMain";  // 슬래시 제거
    }

    // 카테고리별 게시글 조회
    @GetMapping("/posts/category/{category}")
    public String getPostsByCategory(@PathVariable("category") String category, Model model) {
        List<PostDTO> posts = postService.getPostsByCategory(category);
        model.addAttribute("posts", posts);
        return "postMain";  // 슬래시 제거
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
        return "postMain";  // 검색 결과를 postMain 페이지에 렌더링
    }

    // 게시글 작성 페이지로 이동 (GET 요청 처리)
    @GetMapping("/new")
    public String postWritePage(HttpSession session, Model model) {
        UserVO userInfo = getLoginUser(session);
        if (userInfo == null) {
            return "redirect:/login";
        }
        model.addAttribute("userInfo", userInfo);  // 로그인된 사용자 정보 추가
        return "postWrite";  // 게시글 작성 페이지로 이동
    }


    @PostMapping("/new")
    public String createPost(@Valid @ModelAttribute PostDTO postDTO, BindingResult result,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             HttpSession session,
                             Model model) {
        UserVO userInfo = getLoginUser(session);
        if (userInfo == null) {
            return "redirect:/login";  // 로그인 안된 경우 로그인 페이지로 리다이렉트
        }

        // 로그 추가
        System.out.println("게시글 작성 시작");

        // 유효성 검사 실패 시 처리
        if (result.hasErrors()) {
            System.out.println("유효성 검사 실패");
            model.addAttribute("msg", "입력값에 오류가 있습니다.");
            return "postWrite";  // 입력값 오류 시 다시 작성 페이지로 이동
        }

        // 작성자 정보 추가
        postDTO.setUser_id(userInfo.getUser_id());
        postDTO.setNickname(userInfo.getNickname());

        // 파일 업로드 처리
        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "C:/upload/";
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                String filePath = uploadDir + fileName;
                File dest = new File(filePath);
                file.transferTo(dest);  // 파일 저장
                postDTO.setFiles(filePath);

                // 파일 처리 로그 추가
                System.out.println("파일 저장 성공: " + filePath);
            } catch (IOException e) {
                System.out.println("파일 저장 실패");
                model.addAttribute("msg", "파일 저장 중 오류가 발생했습니다.");
                return "postWrite";  // 파일 저장 중 오류 발생 시 다시 작성 페이지로 이동
            }
        }
        // 게시글 등록
        boolean success = postService.register(postDTO);
        System.out.println("게시글 등록 상태: " + success);
        return "postMain";  // 게시글 등록 후 메인 페이지로 리다이렉트
    }


    // 게시글 상세 조회
    @GetMapping("/post/{post_id}")
    public String getPostDetail(@PathVariable int post_id, HttpSession session, Model model) {
        UserVO userInfo = getLoginUser(session);
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);
        }

        PostDTO post = postService.readById(post_id);
        if (post == null) {
            return "error/404"; // 게시글이 없을 경우 404 페이지
        }
        model.addAttribute("post", post);
        return "postDetail";
    }

    // 게시글 수정 페이지
    @GetMapping("/update/{post_id}")
    public String updatePostPage(@PathVariable int post_id, HttpSession session, Model model) {
        UserVO userInfo = getLoginUser(session);
        if (userInfo == null) {
            return "redirect:/login";
        }
        PostDTO post = postService.readById(post_id);
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
        UserVO userInfo = getLoginUser(session);
        if (userInfo == null) {
            return "redirect:/login";
        }
        PostDTO post = postService.readById(post_id);
        if (!post.getNickname().equals(userInfo.getNickname())) {
            return "redirect:/postMain";  // 슬래시 제거 및 redirect로 처리
        }
        if (postService.remove(post_id)) {
            return "redirect:/postMain";  // 슬래시 제거 및 redirect로 처리
        }
        return "postDelete";
    }
}
