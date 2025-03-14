import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String string = scanner.nextLine();
        scanner.close();

        String long_string = "";
        String temporary_string = "";

        for (int i = 0; i < string.length(); i++) {
            if (i == 0 || string.charAt(i) != string.charAt(i - 1)) {
                temporary_string += string.charAt(i);
            } else {
                if (temporary_string.length() > long_string.length()) {
                    long_string = temporary_string;
                }
                temporary_string = "" + string.charAt(i);
            }
        }

        if (temporary_string.length() > long_string.length()) {
            long_string = temporary_string;
        }

        System.out.println("Самая длинная строка с не повторяющимися символами: " + long_string);
    }
}
