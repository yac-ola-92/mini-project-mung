package com.example.mung.controller;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.AccomVO;
import com.example.mung.service.AccomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class AccomController {
    @Autowired
    private AccomService service;

    @PostMapping("/accom_register") //숙소 등록
    @ResponseBody
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
        service.register(vo);
        return "testSuccess";
    }

    @PostMapping("/accom_update")
    @ResponseBody
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
        return "testSuccess";
    }

    @ResponseBody
    @GetMapping("accomByLocation")
    public String accom_list(Model model, HttpServletRequest req){
        String location = req.getParameter("accom_location");
        // 클라이언트로부터 위치 정보를 받아온다
        List<AccomDTO> list;
        if(location == null || location.isEmpty()){
            list = service.findAll();
            //위치를 지정하지 않았다면 전체 숙소를 출력
        }else{
            list = service.readByLocation(req.getParameter("accom_location"));
            // list 에 service의 getListByLocation(위치에 따른 숙소 조회) 한 것을 넣어줌
        }
        model.addAttribute("accom_list",list);
        // list에 전체 숙소의 값 or 해당 지역의 숙소의 값이 있기때문에
        // 이걸 model에 담아 뷰에서 사용할 수 있게 함

        return "여기에 뷰의 이름을 반환할테야";
    }

    @ResponseBody
    @PostMapping("accom_delete")
    public String  accom_delete(@RequestParam int accom_id){
        service.remove(accom_id);
        return "redirect:다시리스트페이지로";
    }
}
