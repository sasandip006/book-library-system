package san.online.lib.book;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<BookModel, UUID> {

    Optional<BookModel> findByIsbn(UUID isbn);

    Page<BookModel> findAllByTitle(String title,Pageable pageable);

    Page<BookModel> findAllByAuthor(String author,Pageable pageable);

    Page<BookModel> findAllByAuthorAndTitle(String author, String title,Pageable pageable);
    
    @Query("SELECT SUM(book.noOfCopies) FROM BookModel book WHERE book.author = :author")
    Long findAllNumberOfBooksCopiesByAuthor(@Param("author") String author);
    
    @Query("SELECT SUM(book.noOfCopies) FROM BookModel book WHERE book.title = :title")
    Long findAllNumberOfBooksCopiesByTitle(@Param("title") String title);
}
