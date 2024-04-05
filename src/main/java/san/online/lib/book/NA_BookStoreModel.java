//package san.online.lib.book;
//
//import static java.sql.Types.VARCHAR;
//
//import java.util.Set;
//import java.util.UUID;
//
//import org.hibernate.annotations.JdbcTypeCode;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode
//@Table(name = "bookstore")
//public class BookStoreModel {
//	@Id
//	@Column(name = "code", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//	@JdbcTypeCode(value = VARCHAR)
//	private UUID code;
//	@NotBlank
//	private String name;
//
//	//@OneToOne(cascade = CascadeType.ALL)
//	//@JoinColumn(name = "add_id",referencedColumnName = "id")
//	@Embedded
//	@AttributeOverrides({@AttributeOverride(name="addressline1", column=@Column(name="ADDRESS_LINE_1"),
//	                     @AttributeOverride(name="addressline2", column=@Column(name="ADDRESS_LINE_2")})
//	private Address address;
//	
//	@OneToMany(mappedBy = "bookStoreModel")
//	private Set<BookModel> bookModel;
//
//}
