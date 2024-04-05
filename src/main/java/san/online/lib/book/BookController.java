package san.online.lib.book;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import san.online.lib.api.ApiResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/books", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @GetMapping
    ApiResponse<List<BookDto>> getBooks(
        @RequestParam(value = "author", required = false) String author,
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "pageNumber",required = false)Integer pageNumber,
        @RequestParam(value = "records",required = true)Integer recordSize) {
        return ApiResponse.ok(bookService.getBooks(author, title,pageNumber,recordSize));
    }

    @PostMapping
    ApiResponse<UUID> createBook(@RequestBody BookDto bookDto) {
        var isbn = bookService.createBook(bookDto);
        return ApiResponse.ok(isbn);
    }

    @PutMapping
    ApiResponse<UUID> updateBook(@RequestBody @Validated BookDto bookDto) {
        var ret = bookService.updateBook(bookDto);
        return ApiResponse.ok(ret);
    }

    @GetMapping("/{isbn}")
    ApiResponse<BookDto> getBookByIsbn(@PathVariable("isbn") @Validated UUID isbn) {
        return ApiResponse.ok(bookService.getBookByIsbn(isbn));
    }

    @DeleteMapping("/{isbn}")
    ApiResponse<Void> deleteBookByIsbn(@PathVariable("isbn") @Validated UUID isbn) {
        bookService.deleteBookWithIsbn(isbn);
        return ApiResponse.ok();
    }
    
    @GetMapping("/isbn/available/copies")
    ApiResponse<BookInventoryDto> getCopiesCountOfBookByIsbn(@RequestParam("isbn") @Validated UUID isbn) {
        return ApiResponse.ok(bookService.getAvailableCopiesByISBN(isbn));
    }
    
    @GetMapping("/author/available/copies")
    ApiResponse<BookInventoryDto> getCopiesCountOfBooksByAuthor(@RequestParam("author") @Validated String author) {
        return ApiResponse.ok(bookService.getAvailableCopiesByAuthor(author));
    }
    
    @GetMapping("/title/available/copies")
    ApiResponse<BookInventoryDto> getCopiesCountOfBooksByTitle(@RequestParam("title") @Validated String title) {
        return ApiResponse.ok(bookService.getAvailableCopiesByTitle(title));
    }

}