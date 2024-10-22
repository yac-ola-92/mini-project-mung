package com.example.mung.controller;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import com.example.mung.service.AccomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class AccomController {
    @Autowired
    private AccomService service;

    @GetMapping("/mainPage")
    public String go(Model model){
        List<AccomDTO>list = service.readByRationg();

        model.addAttribute("accomRating",list);

        return "mainPage";
    }
    @PostMapping("/accom_register") //숙소 등록
    /* 우선 테스트 할 때 반환된 문자열이 자꾸 뷰 템플릿 이름으로 해석 되서
     @ResponseBody를 추가하니까 Spring이 반환된 문장열을 템플릿으로 해석하지 않고
    HTTP 응답 본문으로 직접 반환*/
    public String accom_registration( HttpServletRequest req){
        AccomVO vo = new AccomVO();
         /* 숙소를 등록할 때 하나의 숙소씩 등록이 되기때문에 굳이 리스트를 안쓰고
            그냥 AccomVO객체에 담아도 될 것 같다고 생각함
            유저아이디도 받아와야하나 ?? */
        vo.setUser_id(Integer.parseInt(req.getParameter("user_id")));
        // 유저 아이디는 받을지 말지 추후 합친 후 수정하는 걸루
        vo.setAccom_name(req.getParameter("accom_name"));
        vo.setAccom_location(req.getParameter("accom_location"));
        vo.setAccom_phone(req.getParameter("accom_phone"));
        vo.setAccom_caution(req.getParameter("accom_caution"));
        vo.setAccom_description(req.getParameter("accom_description"));
        vo.setAccom_images_url(req.getParameter("accom_images_url"));
        vo.setAccom_amenities(req.getParameter("accom_amenities"));
        System.out.println(vo);
        service.register(vo);
        return "redirect:/myAccom_list"; //등록했으면 다시 숙소 리스트로 돌아감
    }

    @GetMapping("/myAccom/{accom_id}/edit") //수정할 숙소 불러오기
    //url 요청 접수
    public String accom_edit(@PathVariable int accom_id, Model model){ //id값을 매개변수로 받음
        List<AccomDTO> acc = service.readByAccom_id(accom_id);
        //수정할 데이터들을 받아옴
        model.addAttribute("accomInfo",acc);
        // 모델에 데이터 등록
        return "update_accom";
    }

    @PostMapping("/accom_update") //숙소 수정
    public String accom_update( HttpServletRequest req){
        AccomVO vo = new AccomVO();
        vo.setAccom_id(Integer.parseInt(req.getParameter("accom_id")));
        vo.setAccom_name(req.getParameter("accom_name"));
        vo.setAccom_location(req.getParameter("accom_location"));
        vo.setAccom_phone(req.getParameter("accom_phone"));
        vo.setAccom_caution(req.getParameter("accom_caution"));
        vo.setAccom_description(req.getParameter("accom_description"));
        vo.setAccom_images_url(req.getParameter("accom_images_url"));
        vo.setAccom_amenities(req.getParameter("accom_amenities"));
        service.modify(vo);
        return "redirect:/myAccom_list"; // 마이페이지의 숙소리스트로 돌아갈거임
    }


    @GetMapping("/accomByLocation") //검색 시 출력될 숙소리스트
    public String accom_list(Model model, HttpServletRequest req){
        String location = req.getParameter("accom_location");
        LocalDateTime rv = LocalDate.parse(req.getParameter("rv_start_date")).atStartOfDay();
        String rv_start = new SimpleDateFormat("yyyy-MM-dd").format(rv);
        LocalDateTime rv_last = LocalDate.parse(req.getParameter("rv_end_date")).atStartOfDay();
        String rv_end = new SimpleDateFormat("yyyy-MM-dd").format(rv_last);
        int capacity = Integer.parseInt(req.getParameter("guestCount"));
        // 클라이언트로부터 위치 정보,날짜,인원수를 받아온다
        List<AccomDTO> list;
        if(location == null || location.isEmpty()){
            list = service.readByRationg();
            //위치를 지정하지 않았다면 전체 숙소를 출력
        }else{
            list = service.readByLocation(location,capacity);
            // list 에 service의 getListByLocation(위치에 따른 숙소 조회) 한 것을 넣어줌
        }
        model.addAttribute("accom_location",location);
        model.addAttribute("st_date",rv);
        model.addAttribute("lt_date",rv_last);
        model.addAttribute("capacity",capacity);
        model.addAttribute("accom_list",list);
        // list에 전체 숙소의 값 or 해당 지역의 숙소의 값이 있기때문에
        // 이걸 model에 담아 뷰에서 사용할 수 있게 함

        return "accomList";
    }

    @GetMapping("/accomByAccomId") //상세페이지 숙소
    public String accom_getOne(Model model, HttpServletRequest req ){
        int acc_id = Integer.parseInt(req.getParameter("accom_id"));
        List<AccomDTO> list = service.readByAccom_id(acc_id);
        model.addAttribute("accomGetOne",list);
        //원하는 숙소 클릭 시 나오는 상세페이지에 사용하기 위함
        return "숙소 상세페이지에 리스트 될거야!";
    }

    @GetMapping("/accomByRating")
    public String accom_rating(Model model){
        List<AccomDTO>list = service.readByRationg();
        model.addAttribute("accomRating",list);
        return "accomRating";
    }


    @PostMapping("accom_delete") //숙소 삭제
    public String  accom_delete(@RequestParam int accom_id){
        service.remove(accom_id);
        return "redirect:다시리스트페이지로";
    }
}
