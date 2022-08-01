import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String line = scanner.nextLine();
        scanner.close();
        System.out.println(calc(line));
    }

    final static List<String> roman = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    );

    public static String calc(String input) {
        Scanner sc = new Scanner(input);
        String first, operator, second;
        try {
            first = sc.next();
            operator = sc.next();
            second = sc.next();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lack of arguments");
        }
        if (sc.hasNext()) {
            throw new RuntimeException("Input must contain only 2 operands ans 1 operator");
        }
        int firstInt, secondInt, ans;
        boolean isRoman = false;
        if (roman.contains(first) && roman.contains(second)) {
            isRoman = true;
            firstInt = roman.indexOf(first) + 1;
            secondInt = roman.indexOf(second) + 1;
        } else if (roman.contains(first) || roman.contains(second)) {
            throw new RuntimeException("Numbers must be both arabic or roman");
        } else {
            try {
                firstInt = Integer.parseInt(first);
                secondInt = Integer.parseInt(second);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Numbers must be integer");
            }
        }
        if (Math.max(firstInt, secondInt) > 10 || Math.min(firstInt, secondInt) < 1) {
            throw new RuntimeException("Numbers must be more than 0 and less than 11");
        }
        switch (operator) {
            case "+" -> ans = firstInt + secondInt;
            case "-" -> ans = firstInt - secondInt;
            case "*" -> ans = firstInt * secondInt;
            case "/" -> ans = firstInt / secondInt;
            default -> throw new RuntimeException("Invalid operator");
        }
        if (isRoman) {
            if (ans <= 0) {
                throw new RuntimeException("Roman number must be more than 0");
            }
            return roman.get(ans - 1);
        }
        return String.valueOf(ans);
    }
}
