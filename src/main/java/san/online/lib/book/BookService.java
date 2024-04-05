package san.online.lib.book;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import san.online.lib.bookstore.BookStoreService;
import san.online.lib.exception.BookNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public UUID createBook(BookDto bookDto) {
        BookModel bookModel = toBookModel(bookDto);
        log.warn("Book Model IS "+bookModel.toString());
        var savedBook = bookRepository.save(bookModel);
        return savedBook.getIsbn();
    }

    @CacheEvict(value = "bookCache",key = "#isbn")
    public void deleteBookWithIsbn(@NonNull UUID isbn) {
        bookRepository.deleteById(isbn);
    }
    @Cacheable(value = "bookCache",key = "#p0")
    public BookDto getBookByIsbn(@NonNull UUID isbn) throws BookNotFoundException {
        return bookRepository.findByIsbn(isbn)
            .map(BookService::toBookDto)
            .orElseThrow(() -> new BookNotFoundException(isbn.toString()));
    }
    
    public BookInventoryDto getAvailableCopiesByAuthor(@NonNull String author) throws BookNotFoundException {
    	return  BookInventoryDto.builder().numberOfCopies(bookRepository.findAllNumberOfBooksCopiesByAuthor(author)).build();
    }
    
    public BookInventoryDto getAvailableCopiesByTitle(@NonNull String title) throws BookNotFoundException {
    	return  BookInventoryDto.builder().numberOfCopies(bookRepository.findAllNumberOfBooksCopiesByTitle(title)).build();
    }

    @Cacheable(value = "bookCache", condition = "#p0 != null || #p1 != null")
	public List<BookDto> getBooks(String author, String title, Integer pageNumber, Integer pageSize) {
    	if(pageNumber==null) {
    		pageNumber = 0;
    	}
		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		if (StringUtils.isNotBlank(author) && StringUtils.isNotBlank(title)) {
			return bookRepository.findAllByAuthorAndTitle(author, title, pageable).stream().map(BookService::toBookDto)
					.toList();
		} else if (StringUtils.isNotBlank(author) && StringUtils.isBlank(title)) {
			return bookRepository.findAllByAuthor(author, pageable).stream().map(BookService::toBookDto).toList();
		} else if (StringUtils.isNotBlank(title) && StringUtils.isBlank(author)) {
			return bookRepository.findAllByTitle(title, pageable).stream().map(BookService::toBookDto).toList();
		}

		return bookRepository.findAll(pageable).stream().map(BookService::toBookDto).toList();
	}

	@CachePut(value = "bookCache", key = "#p0.getIsbn()")
    public UUID updateBook(BookDto bookDto) {
        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            var bookModel = toBookModel(bookDto);
            var savedBook = bookRepository.save(bookModel);
            return savedBook.getIsbn();
        }

        throw new BookNotFoundException(bookDto.getIsbn().toString());
    }

    private static BookModel toBookModel(BookDto bookDto) {
        return BookModel.builder()
            .isbn(bookDto.getIsbn())
            .author(bookDto.getAuthor())
            .title(bookDto.getTitle())
            .price(bookDto.getPrice())
            .noOfCopies(bookDto.getNumberOfCopies())
            .bookStoreModel(BookStoreService.toBookStoreModel(bookDto.getBookStoreDto()))
            .build();
    }

    private static BookDto toBookDto(BookModel bookModel) {
        return BookDto.builder()
            .isbn(bookModel.getIsbn())
            .author(bookModel.getAuthor())
            .title(bookModel.getTitle())
            .price(bookModel.getPrice())
            .numberOfCopies(bookModel.getNoOfCopies())
            .bookStoreDto(BookStoreService.toBookStoreDto(bookModel.getBookStoreModel()))
            .build();
    }

	public BookInventoryDto getAvailableCopiesByISBN(UUID isbn) {
		return bookRepository.findByIsbn(isbn)
         .map(BookService::toBookInventoryDto)
         .orElseThrow(() -> new BookNotFoundException(isbn.toString()));
	}
	
	private static BookInventoryDto toBookInventoryDto(BookModel bookModel) {
	    return BookInventoryDto.builder().numberOfCopies(bookModel.getNoOfCopies()).build();	
	}
}
