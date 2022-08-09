package users_books.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Book not found"));
    }

    @Override
    public void createBook(BookEntity book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookEntity updateBook(Long id, BookEntity book) {
        BookEntity update = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Book not found"));
        update.setBookName(book.getBookName());
        update.setAuthorName(book.getAuthorName());
        update.setReleasedYear(book.getReleasedYear());

        return bookRepository.save(update);
    }

    @Override
    public void deleteBooks() {
        bookRepository.deleteAll();
    }

}
