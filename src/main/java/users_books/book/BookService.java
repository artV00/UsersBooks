package users_books.book;

import java.util.List;

public interface BookService {
    List<BookEntity> getBooks();

    BookEntity getBook(Long id);

    void createBook(BookEntity book);

    void deleteBook(Long id);

    BookEntity updateBook(Long id, BookEntity book);

    void deleteBooks();
}
