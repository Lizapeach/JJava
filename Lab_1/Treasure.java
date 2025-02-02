// задание 3
import java.util.Scanner;

public class Treasure {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите координаты клада (x и y через enter): ");

        int x_treasure = scanner.nextInt();
        int y_treasure = scanner.nextInt();

        int x_current = 0;
        int y_current = 0;
        int count = 0;

        while (true) {
            System.out.print("Введите направление и количество шагов (или 'стоп' для выхода): ");
            String direction = scanner.next();

            if (direction.equals("stop")) {
                System.out.println("Вы не дошли до клада :(");
                break;
            }

            int step = scanner.nextInt();

            switch (direction) {
                case "north":
                    y_current += step;
                    break;
                case "south":
                    y_current -= step;
                    break;
                case "east":
                    x_current += step;
                    break;
                case "west":
                    x_current -= step;
                    break;

            }

            count++;

            if (x_current == x_treasure && y_current == y_treasure) {
                System.out.println("Вы дошли до клада за" + count + "шага/шагов");
                break;
            }
        }
        scanner.close();
    }
}
