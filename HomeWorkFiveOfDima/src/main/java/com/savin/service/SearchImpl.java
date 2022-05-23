package com.savin.service;

import com.savin.model.User;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

@Service
public class SearchImpl implements Search{
    private final String FILE = "users.txt";
    private final String regex = "\\r\\n";
    private FileReader fileReader;
    private Scanner scanner;

    @Override
    public User search(User user){
        try {
            String str = searchUser(user.getName(), user.getSurname());
            if (!str.isEmpty()){
                String[] data = str.split(regex);

                user.setName(data[0]);
                user.setSurname(data[1]);
                user.setLastname(data[2]);
                user.setAge(Integer.parseInt(data[3]));
                user.setEmail(data[4]);
                user.setWork(data[5]);
                user.setSalary(Double.parseDouble(data[6]));
            } else {
                user = null;
            }

            return user;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String searchUser(String name, String surname) throws FileNotFoundException {
        fileReader = new FileReader(FILE);
        scanner = new Scanner(fileReader);
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()){
            String nameRead = scanner.nextLine();
            if (name.equals(nameRead)){
                stringBuilder.append(nameRead + System.lineSeparator());
                nameRead = scanner.nextLine();
            }
            if (surname.equals(nameRead)){
                stringBuilder.append(nameRead + System.lineSeparator());

                while (scanner.hasNextLine()){
                    String temp = scanner.nextLine();
                    if (temp.isEmpty()){
                        break;
                    } else {
                        stringBuilder.append(temp + System.lineSeparator());
                    }
                }
            }
        }
        scanner.close();
        return stringBuilder.toString();
    }
}
