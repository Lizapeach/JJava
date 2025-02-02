import java.util.Scanner;

public class Sum_of_a_series {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите положительное целое число: ");

        int n = scanner.nextInt();
        System.out.println("Введите " + n + " чисел/числа через enter:");

        int sum = 0; 
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            if (i % 2 == 0) {
                sum += num; 
            } else {
                sum -= num;
            }
        }
        
        System.out.println("Cумма ряда: " + sum);
        scanner.close();

    }
}
