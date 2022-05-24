package com.savin.service;

import org.springframework.web.multipart.MultipartFile;

public interface WriteUpload {
    void write(String name, MultipartFile file);
}
