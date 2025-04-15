package com.tsy.dto;

import lombok.Data;

import java.util.List;
@Data
public class ChatRequestDTO {
    private List<ChatMessage> messages;
}
