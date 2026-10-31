package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import javax.sound.midi.Patch;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
        boolean isKey = false;
        if (key >= 0 && key < alphabet.length ){
            isKey = true;
        }
        // TODO ключ допустим от 0 до alphabet.length - 1
        //  либо любой ключ приводим к этому диапазону остатком от деления
        return isKey;
    }

    public boolean isFileExists(String filePath) {
        boolean exists = false;
        try {
            Path path = Path.of(filePath);
            if (Files.isRegularFile(path)) {
                exists = true;
            }
            // TODO проверить, что путь существует и ведет к файлу (папка не подходит)

        }catch (InvalidPathException e){
            System.out.println("Не корректный файл");
        }
        return exists;
    }
}
