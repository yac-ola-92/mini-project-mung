package com.example.mung.controller;


import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
public class RoomControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @PostConstruct
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRoom_register()throws Exception{
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("accom_id","7");
        param.add("room_name","진짜집");
        param.add("room_type","패밀리룸");
        param.add("room_price","270000");
        param.add("room_images_url","http://images.realhome");
        param.add("room_info","어서오세요");
        param.add("room_amount","4");
        param.add("pet_kind","중형견");

        mockMvc.perform(post("/room_register")
                        .params(param)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("register success"))
                .andDo(print());
    }
    @Test
    public void testRoom_update()throws Exception{
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("room_id","31");
        param.add("room_name","강아지집");
        param.add("room_type","독채");
        param.add("room_price","210000");
        param.add("room_images_url","http://images.puppyhome");
        param.add("room_info","강아지들을 위한 마당 준비");
        param.add("room_amount","1");
        param.add("pet_kind","대형견");

        mockMvc.perform(post("/room_update")
                        .params(param)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("update success"))
                .andDo(print());
    }
    @Test
    public void testRoom_read() throws Exception {
        mockMvc.perform(get("/roomByAccom")
                        .param("accom_id","8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("여기에 뷰의 이름을 반환할테야"))
                .andDo(print());
    }
    @Test
    public void testRoom_remove() throws Exception{
        mockMvc.perform(post("/room_delete")
                .param("room_id","31")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:다시 리스트 페이지로 이동"))
                .andDo(print());
    }
}
