import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Set<String> authors = new HashSet<>();
    private Map<String, Integer> authorStats = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        authorStats.put(book.getAuthor(), authorStats.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            String author = book.getAuthor();
            int count = authorStats.get(author) - 1;
            if (count == 0) {
                authorStats.remove(author);
                authors.remove(author);
            } else {
                authorStats.put(author, count);
            }
        } else {
            System.out.println("Книга не найдена в библиотеке.");
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void printUniqueAuthors() {
        if (authors.isEmpty()) {
            System.out.println("Нет зарегистрированных авторов.");
        } else {
            System.out.println("Уникальные авторы:");
            for (String author : authors) {
                System.out.println(author);
            }
        }
    }

    public void printAuthorStatistics() {
        if (authorStats.isEmpty()) {
            System.out.println("Нет статистики по авторам.");
        } else {
            System.out.println("Статистика по авторам:");
            for (Map.Entry<String, Integer> entry : authorStats.entrySet()) {
                System.out.printf("%s: %d книг%n", entry.getKey(), entry.getValue());
            }
        }
    }
}
