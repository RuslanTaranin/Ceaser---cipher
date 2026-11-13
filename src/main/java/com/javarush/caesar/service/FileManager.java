package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.FileAlreadyExistsException;

public class FileManager {

        public String readFile(String filePath) throws CipherException {
            try {
                Path path = Path.of(filePath);
                return Files.readString(path, StandardCharsets.UTF_8);
            } catch (InvalidPathException | IOException e) {
                throw new CipherException("Ошибка чтения файла: " + filePath, e);
            }
        }

        public void writeFile(String content, String filePath) throws CipherException {
            try {
                Path path = Path.of(filePath);
                Files.writeString(
                        path,
                        content,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE_NEW
                );
            } catch (FileAlreadyExistsException e) {
                throw new CipherException("Файл уже существует: " + filePath, e);
            } catch (InvalidPathException | IOException e) {
                throw new CipherException("Не удалось записать файл: " + filePath, e);
            }
        }
    }