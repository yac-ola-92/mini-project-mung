package com.example.mung.domain.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Role {

    private String[] role_arr;

    public Role(String[] str){
        this.role_arr = str;
    }
}
