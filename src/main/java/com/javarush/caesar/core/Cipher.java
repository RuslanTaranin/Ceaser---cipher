package com.javarush.caesar.core;


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
        return builder.toString();
    }

    public String decrypt(String encryptedText, int shift) {
            return encrypt(encryptedText, -shift);
        }
    }
