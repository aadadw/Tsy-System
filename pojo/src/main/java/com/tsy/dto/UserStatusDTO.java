package com.tsy.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class UserStatusDTO {
    private Long id;
    private Integer status;
}
