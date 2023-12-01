import java.util.Scanner;

public class Main {
    public static void Main(String[] args) {

        System.out.print("Введите выражение: ");
        Scanner scn = new Scanner(System.in);
        String viraz = scn.nextLine();
        String result = calc(viraz);
        System.out.println(result);
    }


    public static String calc(String viraz) {

        String NOT_MATH_OPER = "строка не является математической операцией";
        String NOT_FORMAT_MATH = "неверное выражение";
        String NOT_ROM_OTRIC = "в римской системе счисления нет отрицательных чисел";
        String NOT_TWO_SS = "разные системы счисления";
        String NOT_SINGL = "неверные числа";

        int actionIndex = -1;
        int operCount = 0;
        Converter converter = new Converter();
        String[] action = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        String ravno;
        char[] actions1 = {'+', '-', '*', '/'};


        for (int i = 0; i < action.length; i++) {
            if (viraz.contains(action[i])) {
                actionIndex = i;
            }
        }
        if (actionIndex == -1) {
            throw new IllegalArgumentException(NOT_MATH_OPER);
        }
        for (int i = 0; i < action.length; i++) {
            operCount += viraz.length() - viraz.replace(String.valueOf(actions1[i]), "").length();
        }
        if (operCount > 1) {
            throw new IllegalArgumentException(NOT_FORMAT_MATH);
        }
        if (operCount == 0) {
            throw new IllegalArgumentException(NOT_MATH_OPER);
        }
        String[] data = viraz.split(regexActions[actionIndex]);

        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;

            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {

                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

                if ((actionIndex == 1) && (a <= b)) {
                    throw new IllegalArgumentException(NOT_ROM_OTRIC);
                }
                if ((0 >= a) | (a > 10) | (0 >= b) | (b > 10)) {
                    throw new IllegalArgumentException(NOT_SINGL);
                }

            } else {

                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if ((0 >= a) | (a > 10) | (0 >= b) | (b > 10)) {
                    throw new IllegalArgumentException(NOT_SINGL);
                }
            }

            int result;
            switch (action[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }

            if (isRoman) {

                System.out.println(converter.intToRoman(result));
            } else {

                System.out.println(result);
            }
        } else {
            throw new IllegalArgumentException(NOT_TWO_SS);
        }


        return NOT_MATH_OPER;
    }
}
