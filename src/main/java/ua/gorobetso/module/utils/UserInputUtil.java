package ua.gorobetso.module.utils;

import java.util.List;
import java.util.Scanner;

public class UserInputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private UserInputUtil() {
    }

    public static int getUserInput(String line, List<String> names) {
        int userInput;
        do {
            System.out.println(line);
            for (int i = 0; i < names.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, names.get(i));
            }
            userInput = SCANNER.nextInt();
        } while (userInput < 1 || userInput >= names.size() + 1);
        return userInput;
    }

    public static String getUserInputString(String line) {
        System.out.println(line);
        String userInput;
        userInput = SCANNER.next();
        return userInput;
    }

    public static int getUserInputChangeInteger(String line) {
        System.out.println(line);
        int userInput;
        userInput = SCANNER.nextInt();
        return userInput;
    }
}
