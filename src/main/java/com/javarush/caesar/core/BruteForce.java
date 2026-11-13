package com.javarush.caesar.core;

public class BruteForce {

    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        String theBestText = "";
        int bestScore = Integer.MIN_VALUE;
        for (int j = 1; j < alphabet.length; j++) {
            StringBuilder builder = new StringBuilder();
            int key = j;

            for (int i = 0; i < encryptedText.length(); i++) {
                char ch = encryptedText.charAt(i);
               int  index = -1;

                for (int k = 0; k < alphabet.length; k++) {
                    if (ch == alphabet[k]){
                        index = k;
                        break;
                    }
                }
                if (index != - 1){
                    int newIndex = Math.floorMod(index - key,alphabet.length);
                    char newChar = alphabet[newIndex];
                    builder.append(newChar);
                }else {
                    builder.append(ch);
                }
            }
            String candidate = builder.toString();
            int score = 0;

            for (int i = 0; i < candidate.length(); i++) {
             if (candidate.charAt(i) == ' '){
                 score++;
             }
            }
            
            if (score > bestScore){
                bestScore = score;
                theBestText = candidate;
            }
        }
    return theBestText;
    }
}
