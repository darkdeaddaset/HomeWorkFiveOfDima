package com.savin.service;

import com.savin.model.User;

import java.io.IOException;

public interface WriteFiles {
    void write(User user) throws IOException;
}
