import java.util.Arrays;
import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите длину массива: ");
    int length = scanner.nextInt();
    int[] array = new int[length];
    System.out.println("Введите элементы этого массива:");
    for (int i = 0; i < length; i++) {
        array[i] = scanner.nextInt();
    }
    scanner.close();
    
    // int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println("Массив: " + Arrays.toString(array));

    int temporary_sum = array[0]; 
    int max_sum = array[0];    

    for (int i = 1; i < array.length; i++) {

        temporary_sum = Math.max(array[i], temporary_sum + array[i]);
        max_sum = Math.max(max_sum, temporary_sum);
    }
    
    System.out.println("Максимальная сумма подмассива: " + max_sum);
    }
}
