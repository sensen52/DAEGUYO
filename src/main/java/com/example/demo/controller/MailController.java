package com.example.demo.controller;

import com.example.demo.domain.dto.EmailComfirmDto;
import com.example.demo.domain.emailService.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final EmailService emailService;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> emailConfirm(@RequestBody Map<String, String> requestData) throws Exception {
        String email = requestData.get("FullEmail");
        String confirm = emailService.sendSimpleMessage(email);
        return ResponseEntity.ok(confirm);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkPhone(@RequestParam("email") String email) {
        EmailComfirmDto dto = emailService.checkEmail(email);
        String serverVerificationCode = dto.getEmailComfirm();

        if (serverVerificationCode != null) {
            return ResponseEntity.ok(serverVerificationCode); // 인증 번호 반환
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 번호가 일치하지 않습니다.");
        }
    }

}
