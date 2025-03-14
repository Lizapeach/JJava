import java.util.Arrays;
import java.util.Scanner;

public class Task_5 {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int length = scanner.nextInt();
        int[] array = new int[length];
        System.out.println("Введите элементы этого массива:");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        // int[] array = {8, 1, 10, 50, 11};
        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Введите целое число:");
        int target = scanner.nextInt();
        // int target = 58;
        // int target = 100;
        scanner.close();

        boolean pair_found = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    System.out.println("Пара элементов которая равна " + target + ": " + array[i] + " " + array[j]);
                    pair_found = true;
                }
            }
        }
        if (pair_found == false){
            System.out.println("Null");
        }
    }
}
