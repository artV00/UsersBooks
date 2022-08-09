package users_books.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books/")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntity> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "{id}")
    public BookEntity getBook(@PathVariable(value = "id") Long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping
    public Long createBook(@RequestBody BookEntity book) {
        bookService.createBook(book);
        return book.getId();
    }

    @PutMapping(path = "{id}")
    public BookEntity updateBook(@PathVariable(value = "id") Long id, @RequestBody BookEntity book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping
    public void deleteBooks() {
        bookService.deleteBooks();
    }
}
