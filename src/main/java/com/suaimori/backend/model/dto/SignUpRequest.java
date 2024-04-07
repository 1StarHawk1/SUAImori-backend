package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.Sex;
import lombok.Data;

import java.sql.Date;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
}
