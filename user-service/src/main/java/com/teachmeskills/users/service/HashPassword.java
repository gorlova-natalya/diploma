package com.teachmeskills.users.service;

public interface HashPassword {

    String hashingPassword(String password);

    boolean validatePassword(String password, String hashedPassword);
}
