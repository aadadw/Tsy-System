package com.tsy.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String role; // "user", "assistant", "system"
    private String content;
}
