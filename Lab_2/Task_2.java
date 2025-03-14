import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Scanner;

public class Task_2 {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину первого массива: ");
        int length_1 = scanner.nextInt();
        int[] array_1 = new int[length_1];
        System.out.println("Введите элементы этого массива:");
        for (int i = 0; i < length_1; i++) {
            array_1[i] = scanner.nextInt();
        }

        System.out.print("Введите длину второго массива: ");
        int length_2 = scanner.nextInt();
        int[] array_2 = new int[length_2];
        System.out.println("Введите элементы этого массива:");
        for (int i = 0; i < length_2; i++) {
            array_2[i] = scanner.nextInt();
        }

        scanner.close();
        // int[] array_1 = {8, 1, 10, 50, 11}; 
        // int[] array_2 = {3, 6, 2, 9, 34}; 

        System.out.println("Первый массив: " + Arrays.toString(array_1));
        System.out.println("Второй массив: " + Arrays.toString(array_2));

        int[] both = ArrayUtils.addAll(array_1, array_2);
        Arrays.sort(both);
        System.out.println("Обьедененый и отсартированный массив" + Arrays.toString(both)); 
    }
}
