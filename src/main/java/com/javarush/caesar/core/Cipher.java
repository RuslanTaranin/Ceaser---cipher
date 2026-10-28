package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

public class Cipher {

    private final char[] alphabet;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    //Зашифровка
    public String encrypt(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = -1;

            for (int j = 0; j < alphabet.length; j++) {
                if (ch == alphabet[j]) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                int newIndex = Math.floorMod(index + shift, alphabet.length);
                char newChar = alphabet[newIndex];
                builder.append(newChar);
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();             //Вопрос: в TODO было сказано сделать перебор быстрым.Как это?Не как не придумал как тольок через 2 цикла(
    }
    //Расшифровка
    public String decrypt(String encryptedText, int shift) throws CipherException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i++) {
            char ch = encryptedText.charAt(i);
            int index = -1;
            for (int j = 0; j < alphabet.length; j++) {
                if (ch == alphabet[j]) {
                    index = j;
                    break;
                }
            }
            if (index == -1){
                throw new CipherException("Данного символа не существует!");
            }else {
                int newIndex = Math.floorMod(index - shift, alphabet.length);
                char newChar = alphabet[newIndex];
                builder.append(newChar);
            }
        }
        return builder.toString();
    }
}

        // TODO сдвинуть каждый символ на shift позиций назад по кругу
        //  символ, которого нет в алфавите, - ошибка: бросаем CipherException



