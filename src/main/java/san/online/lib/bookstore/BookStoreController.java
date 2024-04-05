package san.online.lib.bookstore;

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
@RequestMapping(path = "/api/v1/bookstore", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class BookStoreController {
	 private final BookStoreService bookStoreService;
	
	  @GetMapping
	    ApiResponse<List<BookStoreDto>> getBookStores(
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "city", required = false) String city) {
	        return ApiResponse.ok(bookStoreService.getBookStores(name,city));
	    }

	    @PostMapping
	    ApiResponse<UUID> createBookStore(@RequestBody @Validated BookStoreDto bookStoreDto) {
	        var code = bookStoreService.createBookStore(bookStoreDto);
	        return ApiResponse.ok(code);
	    }

	    @PutMapping
	    ApiResponse<UUID> updateBookStore(@RequestBody @Validated BookStoreDto bookStoreDto) {
	        var ret = bookStoreService.updateBookStore(bookStoreDto);
	        return ApiResponse.ok(ret);
	    }

	    @GetMapping("/{code}")
	    ApiResponse<BookStoreDto> getBookStoreBycode(@PathVariable("code") @Validated UUID code) {
	        return ApiResponse.ok(bookStoreService.getBookStoreByCode(code));
	    }

	    @DeleteMapping("/{code}")
	    ApiResponse<Void> deleteBookStoreBycode(@PathVariable("code") @Validated UUID code) {
	        bookStoreService.deleteBookStoreWithCode(code);
	        return ApiResponse.ok();
	    }
	    

}
