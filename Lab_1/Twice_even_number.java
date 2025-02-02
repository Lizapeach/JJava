import java.util.Scanner;
public class Twice_even_number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите трехзначное число: ");
        int number = scanner.nextInt();
        String str_number = Integer.toString(number);

        int digit_1 = Character.getNumericValue(str_number.charAt(0));
        int digit_2 = Character.getNumericValue(str_number.charAt(1));
        int digit_3 = Character.getNumericValue(str_number.charAt(2));

        int sum = digit_1 + digit_2 + digit_3;
        int product = digit_1 * digit_2 * digit_3;

        if (sum % 2 == 0 && product % 2 == 0) {
            System.out.println("Число дважды четное");
        } else {
            System.out.println("Число не дважды четное");
        }
        scanner.close();
    }
    
}
