package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {

    public String readFile(String filePath) throws CipherException {
        String str = null;
        try {
            Path path = Path.of(filePath);
        str = Files.readString(path, StandardCharsets.UTF_8);
        } catch (InvalidPathException | IOException e) {
            throw new CipherException("Ошибка чтения файла.");
        }
       return str;
    }

    public void writeFile(String content, String filePath) throws CipherException {

        try {
            Path path = Path.of(filePath);
            Files.writeString(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        } catch (InvalidPathException | IOException e) {
            throw new CipherException("Ошибка записи файла.Возможно файл уже существует");
        }
    }
}
