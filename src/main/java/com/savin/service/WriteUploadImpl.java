package com.savin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Scanner;

@Service
public final class WriteUploadImpl implements WriteUpload{
    private final WriteFiles writeFiles;

    @Autowired
    public WriteUploadImpl(WriteFiles writeFiles) {
        this.writeFiles = writeFiles;
    }

    @Override
    public void write(String name, MultipartFile file) {
        try (FileReader fileReader = new FileReader(name + ".txt");
             BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name+".txt")));
             Scanner scanner = new Scanner(fileReader)){
            byte[] bytes = file.getBytes();
            stream.write(bytes);

            while (scanner.hasNextLine()){
                writeFiles.write(scanner.nextLine() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
