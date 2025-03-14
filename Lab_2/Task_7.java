import java.util.Arrays;
import java.util.Scanner;

public class Task_7 {
        public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество колонок матрицы: ");
        int columns = scanner.nextInt();

        int[][] array = new int[rows][columns];
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                array[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        
        // int[][] array = {{5,7}, {7,0},{8,1}};
        System.out.println("Исходная матрица:" + Arrays.deepToString(array));

        int[] max_element = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int max = 0;
            for (int num : array[i]) {
                max = Math.max(max, num);
            }
            max_element[i] = max;
        }
        System.out.println("Максимальный элемент из каждой строки матрицы:" + Arrays.toString(max_element));
    }
}
