import java.util.Arrays;
import java.util.Scanner;

public class Task_4 {
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
    
        int row = array.length;
        int column = array[0].length;

        int[][] new_array = new int[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                new_array[i][j] = array[row - 1 - j][i];
            }
        }
        System.out.println("Повернутая матрица по часовой стрелки на 90 градусов:" + Arrays.deepToString(new_array));
    }   
}
