package com.example.mung.controller;

import com.example.mung.domain.AccomDTO;
import com.example.mung.domain.RoomDTO;
import com.example.mung.domain.RoomVO;
import com.example.mung.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {
    @Autowired
    private RoomService service;


    @PostMapping("/room_register")
    public String room_registration(HttpServletRequest req){
        RoomVO vo = new RoomVO();
        vo.setAccom_id(Integer.parseInt(req.getParameter("accom_id")));
        vo.setRoom_name(req.getParameter("room_name"));
        vo.setRoom_type(req.getParameter("room_type"));
        vo.setRoom_info(req.getParameter("room_info"));
        vo.setRoom_amount(Integer.parseInt(req.getParameter("room_amount")));
        vo.setRoom_images_url(req.getParameter("room_images_url"));
        vo.setRoom_price(Integer.parseInt(req.getParameter("room_price")));
        vo.setPet_kind(req.getParameter("pet_kind"));
        vo.setCapacity_standard(Integer.parseInt(req.getParameter("capacity_standard")));
        vo.setCapacity_standard(Integer.parseInt(req.getParameter("capacity_max")));
        service.register(vo);
        return "register success";
    }

    @GetMapping("/myAccom/{accom_id}/edit") // 숙소 수정에 사용될 정보
    public String room_edit(@PathVariable("accom_id") int accom_id, Model model){
        List<RoomDTO> rm = service.readByAccom_id(accom_id);
        model.addAttribute("rmInfo",rm);
        return "update_accom";
    }


    @PostMapping("/room_update")
    public String room_update(HttpServletRequest req){
        RoomVO vo = new RoomVO();
        vo.setRoom_id(Integer.parseInt(req.getParameter("room_id")));
        vo.setRoom_name(req.getParameter("room_name"));
        vo.setRoom_type(req.getParameter("room_type"));
        vo.setRoom_info(req.getParameter("room_info"));
        vo.setRoom_amount(Integer.parseInt(req.getParameter("room_amount")));
        vo.setRoom_images_url(req.getParameter("room_images_url"));
        vo.setRoom_price(Integer.parseInt(req.getParameter("room_price")));
        vo.setPet_kind(req.getParameter("pet_kind"));
        vo.setCapacity_standard(Integer.parseInt(req.getParameter("capacity_standard")));
        vo.setCapacity_standard(Integer.parseInt(req.getParameter("capacity_max")));
        return "redirect:/myAccom";
    }


    @GetMapping("roomByAccom")
    public String room_list(Model model, HttpServletRequest req){
        int accomId = Integer.parseInt(req.getParameter("accom_id"));
        // 클라이언트로부터 숙소id를 받아와서 그 해당 숙소의 모든 객실을 출력하려고함
        List<RoomDTO> list = service.readByAccom_id(accomId);
        model.addAttribute("room_list",list);
        // list에 숙소의 객실을 출력
        // 이걸 model에 담아 뷰에서 사용할 수 있게 함
        return "여기에 뷰의 이름을 반환할테야";
    }


    @PostMapping("/room_delete")
    public String room_delete(@RequestParam int room_id){
        service.remove(room_id);
        return "redirect:다시 리스트 페이지로 이동";
    }

}

