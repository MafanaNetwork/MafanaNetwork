package me.tahacheji.mafana.util;

import me.tahacheji.mafana.packets.fakePlayer.FakePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakePlayerUtil {
    public static String randomName() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();
        String generatedString = "zz";
        generatedString += random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public String getNextLetter(String startingLetter) {
        StringBuilder nextLetter = new StringBuilder(startingLetter);

        // Check if the last character is 'z'
        int lastIndex = nextLetter.length() - 1;
        if (lastIndex >= 0 && nextLetter.charAt(lastIndex) == 'z') {
            // If the last character is 'z', iterate backward to find the first character that is not 'z'
            while (lastIndex >= 0 && nextLetter.charAt(lastIndex) == 'z') {
                nextLetter.setCharAt(lastIndex, 'a');
                lastIndex--;
            }

            // If all characters are 'z', add a new 'a' at the beginning
            if (lastIndex < 0) {
                nextLetter.insert(0, 'a');
            } else {
                // Increment the non-'z' character
                char incrementedChar = (char) (nextLetter.charAt(lastIndex) + 1);
                nextLetter.setCharAt(lastIndex, incrementedChar);
            }
        } else {
            // Increment the last character if it's not 'z'
            nextLetter.setCharAt(lastIndex, (char) (nextLetter.charAt(lastIndex) + 1));
        }

        return nextLetter.toString();
    }

    public String OLDconvertNumberToLetter(int number) {
        StringBuilder result = new StringBuilder();

        while (number > 0) {
            int remainder = (number - 1) % 26;
            result.insert(0, (char) ('a' + remainder));
            number = (number - 1) / 26;
        }

        return result.toString();
    }


    public String convertNumberToLetter(int input) {
        // Convert the number to a three-digit string
        String numberString = String.format("%03d", input);

        // Convert each digit to the corresponding letter
        StringBuilder result = new StringBuilder();
        for (char digit : numberString.toCharArray()) {
            char ch = (char) ('A' + Integer.parseInt(String.valueOf(digit)));
            result.append(ch);
        }

        return result.toString();
    }


    public List<FakePlayer> generateFakePlayerList(int size) {
        List<FakePlayer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(FakePlayer.randomFakePlayer());
        }
        return list;
    }


}
