package com.savin.controller;

import com.savin.service.WriteUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
    private final WriteUpload writeUpload;

    public UploadController(WriteUpload writeUpload) {
        this.writeUpload = writeUpload;
    }

    @GetMapping("/upload")
    public String menu(){
        return "upload-file";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam String name, @RequestParam MultipartFile file){
        if (file.isEmpty()){
            return "error-upload";
        } else {
            writeUpload.write(name, file);
            return "result-upload";
        }
    }
}
