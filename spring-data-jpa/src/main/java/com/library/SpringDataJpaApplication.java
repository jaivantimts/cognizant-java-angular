package com.library;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository) {
        return args -> {
            System.out.println("=== SPRING DATA JPA - QUICK EXAMPLE ===\n");

            // 1. Save books (CREATE)
            System.out.println("--- Saving books...");
            bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5"));
            bookRepository.save(new Book("1984", "George Orwell", "978-0-452-28423-4"));
            bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4"));
            bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8"));
            System.out.println("✅ Books saved.\n");

            // 2. Find all books (READ)
            System.out.println("--- All books (findAll)...");
            bookRepository.findAll().forEach(System.out::println);
            System.out.println();

            // 3. Find by ID (READ)
            System.out.println("--- Find by ID (1)...");
            bookRepository.findById(1L).ifPresentOrElse(
                book -> System.out.println("Found: " + book),
                () -> System.out.println("Book not found")
            );
            System.out.println();

            // 4. Derived query method
            System.out.println("--- Find by Author (George Orwell)...");
            bookRepository.findByAuthor("George Orwell").forEach(System.out::println);
            System.out.println();

            // 5. Custom JPQL query
            System.out.println("--- Search by title keyword 'Great'...");
            bookRepository.searchByTitleKeyword("Great").forEach(System.out::println);
            System.out.println();

            // 6. Update (using save with existing ID)
            System.out.println("--- Update book ID 1...");
            Book bookToUpdate = bookRepository.findById(1L).orElse(null);
            if (bookToUpdate != null) {
                bookToUpdate.setTitle("The Great Gatsby (Updated)");
                bookRepository.save(bookToUpdate);
                System.out.println("✅ Updated: " + bookToUpdate);
            }
            System.out.println();

            // 7. Delete (DELETE)
            System.out.println("--- Delete book ID 2...");
            bookRepository.deleteById(2L);
            System.out.println("✅ Deleted book ID 2.\n");

            // 8. Final list
            System.out.println("--- Final book list...");
            bookRepository.findAll().forEach(System.out::println);

            System.out.println("\n=== DEMO COMPLETE ===");
        };
    }
}