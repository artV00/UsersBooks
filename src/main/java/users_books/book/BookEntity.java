package users_books.book;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String bookName;
    @Column(nullable = false)
    private String authorName;
    @Column(nullable = false)
    private LocalDate releasedYear;

    public BookEntity() {
        //empty constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(LocalDate releasedYear) {
        this.releasedYear = releasedYear;
    }
}
