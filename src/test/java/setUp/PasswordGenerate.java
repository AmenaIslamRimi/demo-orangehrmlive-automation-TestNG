package setUp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PasswordGenerate {
    public String generatePassword(){
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        String combination = upperCase + lowerCase + numbers + specialChars;
        int length = 8;
        Random random = new Random();
        ArrayList<Character> password = new ArrayList<>();

        // Ensure at least one of each type
        password.add(upperCase.charAt(random.nextInt(upperCase.length())));
        password.add(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.add(numbers.charAt(random.nextInt(numbers.length())));
        password.add(specialChars.charAt(random.nextInt(specialChars.length())));

        // Fill the remaining characters randomly from the full combination
        for (int i = 0; i < length; i++) {
            password.add(combination.charAt(random.nextInt(combination.length())));
        }

        // Shuffle to mix the characters
        Collections.shuffle(password);

        // Convert ArrayList<Character> to a String
        StringBuilder finalPassword = new StringBuilder();
        for (char c : password) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }
}
