// задание 4
import java.util.Scanner;

public class Logistics_maximin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество дорог:");
        int num_roads = scanner.nextInt();

        int max_height = 0;
        int right_road = 0;

        for (int i = 0; i < num_roads; i++) {
            System.out.print("Введите количество тунелей:");
            int num_tunnels = scanner.nextInt();
            int min_height = Integer.MAX_VALUE;

            System.out.print("Введите высоты тунелей:");
            for (int j = 0; j < num_tunnels; j++) {
                int height = scanner.nextInt();
                if (height < min_height) {
                    min_height = height;
                }
            }

            if (min_height > max_height) {
                max_height = min_height;
                right_road = i + 1;
            }
        }

        System.out.println(right_road + " " + max_height);
        scanner.close();
    }
    
}
