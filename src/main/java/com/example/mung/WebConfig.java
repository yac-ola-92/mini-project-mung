package com.example.mung;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//웹 애플리케이션의 정적 리소스에 대한 경로 설정
@Configuration
//WebMvcConfigurer를 구현해서 MVC 설정 조정
public class WebConfig implements WebMvcConfigurer {

    @Override //addResourceHandlers = 정적 리소스 처리할 경로 등록
    //ResourceHandlerRegistry를 사용해서 리소스 경로와 실제 위치 매핑
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler("/uploads/**")= /uploads/로 시작하는 모든 요청을 처리
        registry.addResourceHandler("/uploads/**")
                // 실제 파일이 저장된 경로 지정
                .addResourceLocations("file:///C:/uploads/");
    }
}
