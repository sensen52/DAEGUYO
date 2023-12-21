package com.example.demo.domain.emailService;

import com.example.demo.domain.dto.EmailComfirmDto;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
    public EmailComfirmDto checkEmail(String email);
}
