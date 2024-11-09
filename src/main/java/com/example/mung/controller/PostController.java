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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
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
            posts = postService.searchByNickname(keyword);
        } else {
            posts = new ArrayList<>();
        }
        model.addAttribute("posts", posts);
        return "postMain";
    }

    // 게시글 작성 페이지로 이동 (GET 요청 처리)
    @GetMapping("/new")
    public String postWritePage(HttpSession session, Model model) {
        UserVO userInfo = getLoginUser(session);
        if (userInfo == null) {
            return "redirect:/login";
        }
        model.addAttribute("userInfo", userInfo);
        return "postWrite";
    }

    // db에 이미지파일의 실제 데이터가 저장되지 않고 이미지 경로만 저장.
    // 게시글 작성 처리 (POST 요청 처리)
    @PostMapping("/new")
    public String createPost(@ModelAttribute @Valid PostDTO postDTO,
                             BindingResult bindingResult,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             HttpSession session) throws IOException {
        if (bindingResult.hasErrors()) {
            return "postWrite";
        }
        UserVO userInfo = getLoginUser(session);
        if (userInfo == null) {
            return "redirect:/login";
        }
        postDTO.setUser_id(userInfo.getUser_id());
        postDTO.setNickname(userInfo.getNickname());
        if (file != null && !file.isEmpty()) {
            postDTO.setFiles(file.getBytes());
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1) : "jpeg";
            postDTO.setFileType(fileType);
        }
        if (postService.createPost(postDTO)) {
            return "redirect:/postMain";
        }
        return "postWrite";
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String uploadDir = "C:/uploads/";
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent()); // 디렉토리가 없으면 생성
            file.transferTo(filePath.toFile()); // 파일 저장
            return "/uploads/" + fileName; // 저장된 파일 경로 반환
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/post/{post_id}")
    public String getPostDetail(@PathVariable int post_id, Model model) {
        // 게시글 조회수 증가
        postService.increaseViewCount(post_id); // 조회수 증가 호출

        PostDTO post = postService.readById(post_id);
        if (post == null) {
            return "error/404";
        }

        if (post.getFiles() != null) {
            String mimeType = "image/" + (post.getFileType() != null ? post.getFileType() : "jpeg");
            String base64Image = "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(post.getFiles());
            model.addAttribute("base64Image", base64Image);
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
            return "redirect:/postMain";
        }
        if (postService.remove(post_id)) {
            return "redirect:/postMain";
        }
        return "postDelete";
    }
}