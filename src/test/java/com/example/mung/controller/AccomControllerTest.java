package com.example.mung.controller;

import com.example.mung.domain.AccomDTO;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class AccomControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    //이걸 로딩해서 스프링이 관리하는 모든 빈(컨트롤러, 서비스, 리포지토리 등)이 설정
    private MockMvc mockMvc;
    /* http 호출을 위한 mockMVC 사용
        MockMvc 사용할 경우 실제 컨트롤러 @Autowired 주입 안해도 된다
        MockMvc가 webApplicationContext 를 통해 필요한 모든 빈을 자동으로 설정
   */

    @PostConstruct
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void accom_registration() throws Exception{
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        // MultiValueMap에 추가하는 값은 String 타입으로 변환되기 때문에
        // int 타입의 값의 경우 문자열로 변환해서 추가해야한다
        param.add("user_id","1");
        param.add("accom_name","최종성공을 바라보며");
        param.add("accom_location","서울 송파구");
        param.add("accom_phone","02-7979-8282");
        param.add("accom_amenities","쉽지않다");
        param.add("accom_caution","오늘도 화이팅");
        param.add("accom_description","이번주는 아자아자");
        param.add("accom_images_url","images.Kosa.jpg");
        //parameter의 값들을 넣어줌

        mockMvc.perform(post("/accom_registration")
                        .params(param)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                        .andExpect(status().isOk())
                        .andExpect(content().string("testSuccess"))
                        .andDo(print());
        // POST방식으로 /accom_registration 을 호출
        // params에 위에 설정한 parameter 값을 주고
        //contentType에 API호출 결과 값의 유형을 설정 json 형식으로 데이터 send
        //HTTP 200을 기대 성공 되냐 이거임
        //andDo(print())화면에 결과 출력
    }

    @Test
    public void accom_update() throws Exception{
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("accom_id","30");
        param.add("accom_name","자율학습");
        param.add("accom_location","강원도 양구");
        param.add("accom_phone","010-5461-1123");
        param.add("accom_amenities","스케이트보드 타고싶다");
        param.add("accom_caution","겨울은 언제쯤");
        param.add("accom_description","눈왔으면 좋겠다");
        param.add("accom_images_url","images.snow.jpg");

        mockMvc.perform(post("/accom_update")
                        .params(param)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("testSuccess"))
                .andDo(print());
    }

    @Test
    public void accom_list() throws Exception{


    }
   /* @GetMapping("accom_list")
    public String accom_list(Model model){
        List<AccomDTO> list = service.getList();
        // list 에 service의 getlist(전체 숙소를 조회한 것) 한 것을 넣어줌
        model.addAttribute("accom_list",list);
        // list에 전체 숙소의 값들이 있으니까 이걸 model에 담아 뷰에서 사용할 수 있게 함

        return "여기에 뷰의 이름을 반환할테야";
    }*/
}
