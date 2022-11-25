package com.gl.controller;

import com.gl.model.Email;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("email")
@CrossOrigin(origins = "*")
public class EmailController {

    @PostMapping
    public void sendEmail(@NonNull @RequestBody Email email) {
        try {
            email.sendEmail();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}