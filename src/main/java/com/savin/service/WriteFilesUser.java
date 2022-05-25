package com.savin.service;

import com.savin.model.User;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

@Service
public final class WriteFilesUser implements WriteFiles{
    private final String EMPTY = "Unknown";
    private final String NAME_FILE = "users.txt";

    @Override
    public void write(User user) throws IOException {
        try (FileWriter fileWriter = new FileWriter(NAME_FILE, true)){
            edit(user);

            fileWriter.write(user.getName());
            fileWriter.write(System.lineSeparator());
            fileWriter.write(user.getSurname());
            fileWriter.write(System.lineSeparator());
            fileWriter.write(user.getLastname());
            fileWriter.write(System.lineSeparator());
            fileWriter.write(Integer.toString(user.getAge()));
            fileWriter.write(System.lineSeparator());
            fileWriter.write(user.getEmail());
            fileWriter.write(System.lineSeparator());
            fileWriter.write(user.getWork());
            fileWriter.write(System.lineSeparator());
            fileWriter.write(Double.toString(user.getSalary()));
            fileWriter.write(System.lineSeparator());
            fileWriter.write(System.lineSeparator());
        }
    }

    @Override
    public void write(String str) {
        try (FileWriter fileWriter = new FileWriter(NAME_FILE, true)){

            fileWriter.write(str);
        } catch (IOException e) {
            System.out.println("Recording is not possible newUser");
            throw new RuntimeException(e);
        }
    }

    private void edit(User user){
        if (user.getWork().isEmpty()){
            editCompany(user);
        }
    }
    private void editCompany(User user){
        user.setWork(EMPTY);
    }
}
