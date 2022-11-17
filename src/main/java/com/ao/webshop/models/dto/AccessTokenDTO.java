package com.ao.webshop.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessTokenDTO implements Dto {
    private String accessToken;
}
