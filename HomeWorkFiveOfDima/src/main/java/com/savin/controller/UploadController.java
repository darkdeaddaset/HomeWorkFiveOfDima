package com.savin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {

    @GetMapping("/upload")
    public String menu(){
        return "Вы можете загрузить свой файл";
    }


}
