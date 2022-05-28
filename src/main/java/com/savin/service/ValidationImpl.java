package com.savin.service;

import com.savin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public final class ValidationImpl implements Validation{
    private final String PATTERN_NAME = "[A-Za-z]+";
    private final String PATTERN_EMAIL = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private final WriteFiles writeFiles;

    @Autowired
    public ValidationImpl(WriteFiles writeFiles) {
        this.writeFiles = writeFiles;
    }

    /*@Override
    public boolean check(User user){
        if (checkName(user.getName()) && checkName(user.getLastname()) &&
                checkName(user.getSurname()) && checkEmail(user.getEmail()) &&
                checkAge(user.getAge()) && checkSalary(user.getSalary())){
            try {
                writeFiles.write(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }*/

    //Альтернатива
    public boolean check(User user){
        boolean b = checkAll(user);
        if (b){
            try {
                writeFiles.write(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return b;
    }

    private boolean checkName(String name){
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    private boolean checkEmail(String email){
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean checkAge(int age){
        return age>0 && age<100;
    }

    private boolean checkSalary(double salary){
        return salary>0 && salary<1000000;
    }

    public boolean checkAll(User user){
        return checkName(user.getName()) && checkName(user.getLastname()) &&
                checkName(user.getSurname()) && checkEmail(user.getEmail()) &&
                checkAge(user.getAge()) && checkSalary(user.getSalary());
    }
}