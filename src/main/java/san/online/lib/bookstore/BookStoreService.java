package san.online.lib.bookstore;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import san.online.lib.exception.BookNotFoundException;
import san.online.lib.exception.BookStoreNotFoundException;

@Service
@RequiredArgsConstructor
public class BookStoreService {
	private final BookStoreRepository bookStoreRepository;
	 public UUID createBookStore(BookStoreDto bookStoreDto) {
	        BookStoreModel bookModel = toBookStoreModel(bookStoreDto);
	        var savedBook = bookStoreRepository.save(bookModel);
	        return savedBook.getCode();
	    }

	    public void deleteBookStoreWithCode(@NonNull UUID code) {
	        bookStoreRepository.deleteById(code);
	    }

	    public BookStoreDto getBookStoreByCode(@NonNull UUID code) throws BookNotFoundException {
	        return bookStoreRepository.findById(code)
	            .map(BookStoreService::toBookStoreDto)
	            .orElseThrow(() -> new BookNotFoundException(code.toString()));
	    }
	    

		public List<BookStoreDto> getBookStores(String author,String city) {
			return bookStoreRepository.findAll().stream().map(BookStoreService::toBookStoreDto).toList();
		}

	    public UUID updateBookStore(BookStoreDto bookStoreDto) {
	        if (bookStoreRepository.findById(bookStoreDto.getCode()).isPresent()) {
	            var bookModel = toBookStoreModel(bookStoreDto);
	            var savedBook = bookStoreRepository.save(bookModel);
	            return savedBook.getCode();
	        }

	        throw new BookStoreNotFoundException(bookStoreDto.getCode().toString());
	    }

	    public static BookStoreModel toBookStoreModel(BookStoreDto bookStoreDto) {
	        return BookStoreModel.builder()
	            .code(bookStoreDto.getCode())
	            .name(bookStoreDto.getName())
	            .address(bookStoreDto.getAddress())
	            .build();
	    }

	    public static BookStoreDto toBookStoreDto(BookStoreModel bookModel) {
	        return BookStoreDto.builder()
	            .code(bookModel.getCode())
	            .name(bookModel.getName())
	            .address(bookModel.getAddress())
	            .build();
	    }
}
