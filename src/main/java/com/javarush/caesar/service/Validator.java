package com.javarush.caesar.service;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
        boolean isKey = false;
        if (key >= 0 && key < alphabet.length ){
            isKey = true;
        }
        return isKey;
    }

    public boolean isFileExists(String filePath) {
        boolean exists = false;
        try {
            Path path = Path.of(filePath);
            if (Files.isRegularFile(path)) {
                exists = true;
            }
        }catch (InvalidPathException e){
            System.out.println("Не корректный файл");
        }
        return exists;
    }
}
