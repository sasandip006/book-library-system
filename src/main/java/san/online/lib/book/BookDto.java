package san.online.lib.book;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import san.online.lib.bookstore.BookStoreDto;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotNull
    private UUID isbn;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    @DecimalMin(value = "0.00", message = "Book price must be higher than 0.00")
    private BigDecimal price;

    @NotNull
    @Min(value = 0,message = "Book copies should be more than zero")
    private Long numberOfCopies;
    
    @NotNull
    @JsonProperty
    private BookStoreDto bookStoreDto;
    
}