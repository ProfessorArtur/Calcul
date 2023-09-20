package arabian;

import java.util.Scanner;

public class Main {
    public static void main(String[] input) {
        try {
            Converter converter = new Converter();
            String[] actions = {"+", "-", "/", "*"};
            String[] regexActions = {"\\+", "-", "/", "\\*"};
            Scanner scn = new Scanner(System.in);
            String exp = scn.nextLine();
            int actionIndex = -1;
            for (int i = 0; i < actions.length; i++) {
                if (exp.contains(actions[i])) {
                    actionIndex = i;
                    break;
                }
            }
            if (actionIndex == -1) {
                throw new IllegalArgumentException("Некорректное выражение");
            }
            String[] data = exp.split(regexActions[actionIndex]);
            if (data.length != 2) {
                throw new IllegalArgumentException("В выражении должны быть только два операнда");
            }
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                int a, b;
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);
                } else {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
                }
                int result = switch (actions[actionIndex]) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };
                if (isRoman) {
                    System.out.println(converter.intToRoman(result));
                } else {
                    System.out.println(result);
                }
            } else {
                throw new IllegalArgumentException("Числа должны быть в одном формате");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
