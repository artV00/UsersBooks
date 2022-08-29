package users_books.book;

import java.util.List;

public interface BookService {
    List<BookEntity> getBooks();

    BookEntity getBook(long id);

    BookEntity createBook(BookEntity book);

    void deleteBook(long id);

    BookEntity updateBook(long id, BookEntity book);

    void deleteBooks();
}
