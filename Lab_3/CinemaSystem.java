import java.util.*;

class User {
    String username;
    String password;
    boolean isAdmin;

    User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}

class Seat {
    boolean isTaken = false;
}

class Hall {
    String name;
    Seat[][] seats;
    List<Session> sessions = new ArrayList<>();

    Hall(String name, int rows, int cols) {
        this.name = name;
        this.seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                seats[i][j] = new Seat();
    }

    void printSeatMap(Session session) {
        Seat[][] map = session.seatMap;
        for (int i = 0; i < map.length; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].isTaken ? "[X]" : "[O]");
            }
            System.out.println();
        }
    }
}

class Cinema {
    String name;
    List<Hall> halls = new ArrayList<>();

    Cinema(String name) {
        this.name = name;
    }
}

class Movie {
    String title;
    int duration; // in minutes

    Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}

class Session {
    Movie movie;
    Date time;
    Hall hall;
    Seat[][] seatMap;

    Session(Movie movie, Date time, Hall hall) {
        this.movie = movie;
        this.time = time;
        this.hall = hall;
        seatMap = new Seat[hall.seats.length][hall.seats[0].length];
        for (int i = 0; i < seatMap.length; i++)
            for (int j = 0; j < seatMap[i].length; j++)
                seatMap[i][j] = new Seat();
    }
}

public class CinemaSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Cinema> cinemas = new ArrayList<>();
    static List<Movie> movies = new ArrayList<>();
    static User currentUser = null;

    public static void main(String[] args) {
        init();
        while (true) {
            System.out.println("1. Вход\n2. Регистрация\n3. Выход");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": login(); break;
                case "2": register(); break;
                case "3": return;
                default: System.out.println("Неверный ввод");
            }
        }
    }

    static void init() {
        users.add(new User("admin", "admin", true));
    }

    static void login() {
        System.out.print("Логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        for (User u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                currentUser = u;
                if (u.isAdmin) adminMenu(); else userMenu();
                return;
            }
        }
        System.out.println("Неверные данные");
    }

    static void register() {
        System.out.print("Придумайте логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        users.add(new User(username, password, false));
        System.out.println("Регистрация завершена");
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n1. Добавить кинотеатр\n2. Добавить зал\n3. Добавить фильм\n4. Добавить сеанс\n5. Выход");
            String ch = scanner.nextLine();
            switch (ch) {
                case "1":
                    System.out.print("Название кинотеатра: ");
                    cinemas.add(new Cinema(scanner.nextLine()));
                    break;
                case "2":
                    Cinema c = selectCinema();
                    System.out.print("Название зала: ");
                    String hallName = scanner.nextLine();
                    System.out.print("Рядов: ");
                    int rows = Integer.parseInt(scanner.nextLine());
                    System.out.print("Мест в ряду: ");
                    int cols = Integer.parseInt(scanner.nextLine());
                    c.halls.add(new Hall(hallName, rows, cols));
                    break;
                case "3":
                    System.out.print("Название фильма: ");
                    String title = scanner.nextLine();
                    System.out.print("Длительность (мин): ");
                    int dur = Integer.parseInt(scanner.nextLine());
                    movies.add(new Movie(title, dur));
                    break;
                case "4":
                    Movie m = selectMovie();
                    Cinema cin = selectCinema();
                    Hall h = selectHall(cin);
                    System.out.print("Введите дату и время (yyyy-mm-dd hh:mm): ");
                    Date time = new Date();
                    try {
                        time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Неверный формат");
                        break;
                    }
                    Session sess = new Session(m, time, h);
                    h.sessions.add(sess);
                    break;
                case "5": return;
                default: System.out.println("Неверный ввод");
            }
        }
    }

    static void userMenu() {
        while (true) {
            System.out.println("\n1. Просмотр сеансов по фильму\n2. Купить билет\n3. Выход");
            String ch = scanner.nextLine();
            switch (ch) {
                case "1":
                    Movie m = selectMovie();
                    for (Cinema c : cinemas) {
                        for (Hall h : c.halls) {
                            for (Session s : h.sessions) {
                                if (s.movie == m) {
                                    System.out.println("Сеанс: " + s.time + " в зале " + h.name + " кинотеатр " + c.name);
                                }
                            }
                        }
                    }
                    break;
                case "2":
                    Movie mov = selectMovie();
                    List<Session> allSessions = new ArrayList<>();
                    for (Cinema c : cinemas) {
                        for (Hall h : c.halls) {
                            for (Session s : h.sessions) {
                                if (s.movie == mov) allSessions.add(s);
                            }
                        }
                    }
                    for (int i = 0; i < allSessions.size(); i++) {
                        Session s = allSessions.get(i);
                        System.out.println((i + 1) + ". " + s.time + " – " + s.hall.name + " – " + s.hall.name);
                    }
                    System.out.print("Выберите номер сеанса: ");
                    int si = Integer.parseInt(scanner.nextLine()) - 1;
                    Session selected = allSessions.get(si);
                    selected.hall.printSeatMap(selected);
                    System.out.print("Ряд: ");
                    int row = Integer.parseInt(scanner.nextLine()) - 1;
                    System.out.print("Место: ");
                    int col = Integer.parseInt(scanner.nextLine()) - 1;
                    if (!selected.seatMap[row][col].isTaken) {
                        selected.seatMap[row][col].isTaken = true;
                        System.out.println("Билет куплен!");
                    } else {
                        System.out.println("Место уже занято");
                    }
                    break;
                case "3": return;
                default: System.out.println("Неверный ввод");
            }
        }
    }

    static Cinema selectCinema() {
        for (int i = 0; i < cinemas.size(); i++)
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        System.out.print("Выберите кинотеатр: ");
        return cinemas.get(Integer.parseInt(scanner.nextLine()) - 1);
    }

    static Movie selectMovie() {
        for (int i = 0; i < movies.size(); i++)
            System.out.println((i + 1) + ". " + movies.get(i).title);
        System.out.print("Выберите фильм: ");
        return movies.get(Integer.parseInt(scanner.nextLine()) - 1);
    }

    static Hall selectHall(Cinema c) {
        for (int i = 0; i < c.halls.size(); i++)
            System.out.println((i + 1) + ". " + c.halls.get(i).name);
        System.out.print("Выберите зал: ");
        return c.halls.get(Integer.parseInt(scanner.nextLine()) - 1);
    }
}
