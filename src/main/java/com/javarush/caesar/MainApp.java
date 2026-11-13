package com.javarush.caesar;

import com.javarush.caesar.core.BruteForce;
import com.javarush.caesar.core.Cipher;
import com.javarush.caesar.exception.CipherException;
import com.javarush.caesar.service.FileManager;
import com.javarush.caesar.service.Validator;

import java.util.Scanner;

public class MainApp {

    private static final char[] ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
            'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
    };

    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final Validator validator = new Validator();
    private final Cipher cipher = new Cipher(ALPHABET);
    private final BruteForce bruteForce = new BruteForce();
    public static void main(String[] args) {

        new MainApp().run();
    }

    private void run() {
        System.out.println("Шифр Цезаря: шифрование и расшифровка файлов");
        while (true) {
            printMenu();
            String choice = ask("Выберите действие: ");
            try {
                switch (choice) {
                    case "1":
                        encryptFile();
                        break;
                    case "2":
                        decryptFile();
                        break;
                    case "3":
                        bruteForceFile();
                        break;
                    case "0":
                    case "exit":
                        System.out.println("До свидания!");
                        return;
                    default:
                        System.out.println("Нет такого пункта: " + choice);
                }
            } catch (CipherException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с ключом");
        System.out.println("3. Взломать перебором ключей");
        System.out.println("0. Выход (или exit)");
    }

    private void encryptFile() throws CipherException {
        System.out.println("Введите пожалуйста исходный файл");
        String readFiles = scanner.nextLine();
        if (validator.isFileExists(readFiles)) {
            System.out.println("Введите куда хотите записать зашифрованные текст");
        } else {
            throw new CipherException("Ошибка чтения файла");
        }
        String writeFiles = scanner.nextLine();
        System.out.println("Введите ключ");
        try {
            String keyLine = scanner.nextLine();
            int key = Integer.parseInt(keyLine);


            if (validator.isValidKey(key, ALPHABET)) {
                String fileManagerStr = fileManager.readFile(readFiles);
                String encryptFile = cipher.encrypt(fileManagerStr, key);
                fileManager.writeFile(encryptFile, writeFiles);
            }else{
                System.out.println("Не корректный ввод ключа");
            }

        }catch (NumberFormatException e){
            System.out.println("Ключ должен быть числом");
        }
    }

    private void decryptFile() throws CipherException {
        System.out.println("Введите пожалуйста исходный файл");
        String readFiles = scanner.nextLine();
        if (validator.isFileExists(readFiles)){
            System.out.println("Введите куда хотите записать расшифрованный текст");
        }else {
            throw new CipherException("Ошибка чтения файла");
        }
        String writeFiles = scanner.nextLine();
        System.out.println("Введите ключ");
        try {


            String keyLine = scanner.nextLine();
            int key = Integer.parseInt(keyLine);
            if (validator.isValidKey(key, ALPHABET)) {
                String fileManagerStr = fileManager.readFile(readFiles);
                String decryptFile = cipher.decrypt(fileManagerStr, key);
                fileManager.writeFile(decryptFile, writeFiles);
            }else{
                System.out.println("Не корректный ввод ключа");
            }
        }catch (NumberFormatException e){
            System.out.println("Ключ должен быть числом");
        }
    }

    private void bruteForceFile() throws CipherException {
        System.out.println("Введите зашифрованный файл");
        String encryptedFiles = scanner.nextLine();
        if (validator.isFileExists(encryptedFiles)){
            System.out.println("Введите файл куда хотите записать расшифрованный файл");
        }else {
          throw new  CipherException("Ошибка чтения файла");
        }
        String writeFiles = scanner.nextLine();
        String readEncryptedFiles = fileManager.readFile(encryptedFiles);
        String decryptedFiles = bruteForce.decryptByBruteForce(readEncryptedFiles,ALPHABET);
        fileManager.writeFile(decryptedFiles,writeFiles);
    }

    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
