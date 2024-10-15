package com.example.mung.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVO {

    private int company_id;
    private String company_name;
    private String company_address;
    private String company_phone;
    private String company_email;
    private LocalDateTime established_date;
}
