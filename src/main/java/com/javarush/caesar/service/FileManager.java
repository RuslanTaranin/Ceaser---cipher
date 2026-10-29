package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {

    public String readFile(String filePath) throws CipherException {
        Path path = Path.of(filePath);
        String str = null;
        try {
         str = Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CipherException("Ошибка чтения файла.");
        }
       return str;
    }

    public void writeFile(String content, String filePath) throws CipherException {
        Path path = Path.of(filePath);

        try {
            Files.writeString(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new CipherException("Ошибка записи файла.");
        }
    }
}
