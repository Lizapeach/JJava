//задание 1
import java.util.Scanner;

public class Collatz_hypothesis {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите положительное целое число: ");

        int number = scanner.nextInt();
        int count = 0;

        while (number != 1){
            if (number % 2 == 0){
                number = number / 2;
            }
            else{
                number = number * 3 + 1;
            }
            count++;
        }

        System.out.println("Количество шагов: " + count);
        scanner.close();
    }
}
