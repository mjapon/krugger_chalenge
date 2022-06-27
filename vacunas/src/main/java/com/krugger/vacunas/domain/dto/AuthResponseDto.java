package com.krugger.vacunas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private Long empid;
    private String userinfo;
    private String token;
}
