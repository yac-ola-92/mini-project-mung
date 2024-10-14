package com.example.mung.domain.transfer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private List<String> role;

    public Role(String role) {
        this.role = Arrays.asList(role.split(","));
    }

    public void setRole(String role) {
        this.role = Arrays.asList(role.split(","));
    }

    public String getRoleAdString() {
        return String.join(",", this.role);
    }
}
