public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        // Добавление книг
        Book b1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book b2 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book b3 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866);
        Book b4 = new Book("Идиот", "Фёдор Достоевский", 1869);
        Book b5 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.addBook(b5);

        // Вывод всех книг
        System.out.println("📚 Все книги:");
        library.printAllBooks();

        // Поиск по автору
        System.out.println("\n🔍 Книги Достоевского:");
        for (Book b : library.findBooksByAuthor("Фёдор Достоевский")) {
            System.out.println(b);
        }

        // Поиск по году
        System.out.println("\n📅 Книги за 1869 год:");
        for (Book b : library.findBooksByYear(1869)) {
            System.out.println(b);
        }

        // Статистика
        System.out.println("\n👤 Уникальные авторы:");
        library.printUniqueAuthors();

        System.out.println("\n📈 Статистика по авторам:");
        library.printAuthorStatistics();

        // Удаление книги
        System.out.println("\n❌ Удаляем \"Анна Каренина\"");
        library.removeBook(b2);

        // Проверка после удаления
        System.out.println("\n📚 Книги после удаления:");
        library.printAllBooks();

        System.out.println("\n📈 Статистика после удаления:");
        library.printAuthorStatistics();
    }
}
