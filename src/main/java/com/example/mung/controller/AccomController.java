package com.example.mung.controller;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import com.example.mung.domain.UserVO;
import com.example.mung.service.AccomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Pageable;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.util.List;


@Controller
public class AccomController {
    @Autowired
    private AccomService service;


    @GetMapping("/mainPage") // 메인페이지 이동
    public String go(Model model, HttpSession session){
        List<AccomDTO>list = service.readByRating();
        UserVO check = null;
        if (session.getAttribute("userInfo")!=null){
            check=(UserVO) session.getAttribute("userInfo");
        }
        System.out.println(check != null ? check.getRole() : null);
        System.out.println(list);
        if(check ==null){
           model.addAttribute("accomRating", list);
        }else {
            model.addAttribute("userCheck", check.getUser_id());
            model.addAttribute("role", check.getRole());
            model.addAttribute("accomRating", list);
        }

        return "mainPage";
    }
    @GetMapping("/accom_register") //숙소 등록 페이지 이동
    public String goReg (HttpSession session){
        String result ;
        UserVO check= (UserVO) session.getAttribute("userInfo");
        if (check==null){
            System.out.println("로그인해야합니다");
            return "redirect:/login";
        }
        return "/register_accom";
}

//@ResponseBody
@PostMapping("/accom_register") //숙소 등록
    /* 우선 테스트 할 때 반환된 문자열이 자꾸 뷰 템플릿 이름으로 해석 되서
     @ResponseBody를 추가하니까 Spring이 반환된 문장열을 템플릿으로 해석하지 않고
    HTTP 응답 본문으로 직접 반환*/
public String accom_registration(HttpServletRequest req, HttpSession session ){
    AccomVO vo = new AccomVO();
      /* 숙소를 등록할 때 하나의 숙소씩 등록이 되기때문에 굳이 리스트를 안쓰고
            그냥 AccomVO객체에 담아도 될 것 같다고 생각함
            유저아이디도 받아와야하나 ?? */
    UserVO check= (UserVO) session.getAttribute("userInfo");
    System.out.println(check.getUser_id()+"사용자의 숙소등록");
    vo.setUser_id(check.getUser_id());
    vo.setAccom_name(req.getParameter("accom_name"));
    vo.setAccom_location(req.getParameter("accom_location"));
    vo.setAccom_phone(req.getParameter("accom_phone"));
    vo.setAccom_caution(req.getParameter("accom_caution"));
    vo.setAccom_description(req.getParameter("accom_description"));
    vo.setAccom_images_url(req.getParameter("accom_images_url"));
    vo.setAccom_amenities(req.getParameter("accom_amenities"));
    System.out.println(vo);
    service.register(vo);

    return "/mainPage";
    //등록했으면 다시 숙소 리스트로 돌아감
}

@GetMapping("/myAccom/edit/{accom_id}") //수정할 숙소 불러오기
//url 요청 접수
public String accom_edit(@PathVariable int accom_id, Model model){ //id값을 매개변수로 받음
    List<AccomDTO> acc = service.readByAccom_id(accom_id);
    //수정할 데이터들을 받아옴
    if(acc!=null){
        // 모델에 데이터 등록
        model.addAttribute("accInfo",acc);
    }else {
        return "redirect:/error/404";
    }
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
    return "숙소리스트로 "; // 마이페이지의 숙소리스트로 돌아갈거임
}

@GetMapping("/accomByLocation") //검색 시 출력될 숙소리스트
public String accom_list(Model model, HttpServletRequest req) {
    // 클라이언트로부터 위치 정보,날짜,인원수를 받아온다
    List<AccomDTO> list;
    String location = req.getParameter("accom_location");
    String rv_s = req.getParameter("rv_start_date");
    String rv_l = req.getParameter("rv_end_date");
    LocalDate rv_start;
    LocalDate rv_end;
    //날짜 처리
    if (rv_s == null || rv_l == null || rv_s.isEmpty() || rv_l.isEmpty()) {
        rv_start = LocalDate.now();
        rv_end = rv_start.plusDays(1);
        System.out.println("날짜를 입력하지 않아 오늘을 기준으로 검색합니다");
        //날짜를 입력하지 않았을 경우 오늘과 내일을 기준으로 숙소가 출력
    } else {
        rv_start = LocalDate.parse(rv_s);
        rv_end = LocalDate.parse(rv_l);
    }
    System.out.println(rv_start + "부터" + rv_end + "까지 날짜 설정");
    String capa = req.getParameter("capacity");
    int capacity = 2; //기본값 지정
    //인원 체크
    if (capa != null && !capa.isEmpty()) {
        try {//인원 null 값  체크
            capacity = Integer.parseInt(capa);
        }
        catch(NumberFormatException e){
        System.out.println("인원 수를 입력하지 않음!!!");
         }
    }else {
        System.out.println("기본값 2 를 적용하여 출력합니다");
    }
  // 위치 체크
    if(location == null || location.isEmpty()){
        list = service.readByRating();
        //위치를 지정하지 않았다면 전체 숙소를 출력
    }else{
        list = service.readByLocation(location,capacity);
        // list 에 service의 getListByLocation(위치에 따른 숙소 조회) 한 것을 넣어줌
    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    model.addAttribute("accom_location",location);
    model.addAttribute("st_date",rv_start.format(dtf));
    model.addAttribute("lt_date",rv_end.format(dtf));
    model.addAttribute("capacity",capacity);
    model.addAttribute("accomList",list);
    // list에 전체 숙소의 값 or 해당 지역의 숙소의 값이 있기때문에
    // 이걸 model에 담아 뷰에서 사용할 수 있게 함
    System.out.println(location);
    System.out.println(rv_start.format(dtf));
    System.out.println(rv_end.format(dtf));
    System.out.println(capacity);
    return "accomList";
}
@GetMapping("/accom/{accom_id}/byAccomId") //숙소의 상세페이지
public String accom_getOne(@PathVariable int accom_id, Model model, HttpServletRequest req ){
    int acc_id = Integer.parseInt(req.getParameter("accom_id"));
    List<AccomDTO> dto = service.readByAccom_id(acc_id);
    model.addAttribute("accInfo",dto);
    //원하는 숙소 클릭 시 나오는 상세페이지에 사용하기 위함
    return "/accomDetail";
}

@PostMapping("/accom_delete") //숙소 삭제
public String  accom_delete(@RequestParam int accom_id){
    service.remove(accom_id);
    return "redirect:다시리스트페이지로";
}

}
