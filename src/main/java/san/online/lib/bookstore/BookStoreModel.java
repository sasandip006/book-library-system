package san.online.lib.bookstore;

import static java.sql.Types.VARCHAR;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import san.online.lib.book.BookModel;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "bookstore")
public class BookStoreModel {
	@Id
	@Column(name = "code", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@JdbcTypeCode(value = VARCHAR)
	private UUID code;
	@NotBlank
	private String name;

	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "bookStoreModel",fetch = FetchType.LAZY)
	private Set<BookModel> bookModel;

}
