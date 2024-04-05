//package san.online.lib.book;
//
//import static java.sql.Types.VARCHAR;
//
//import java.util.UUID;
//
//import org.hibernate.annotations.JdbcTypeCode;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
////@Entity
////@Table(name="address")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode
//@Embeddable  //or @AttributeOverrides
//public class Address {
//	
//	@Id
//    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//    @JdbcTypeCode(value = VARCHAR)
//	private UUID id;
//	@NotBlank
//	@Enumerated(EnumType.STRING)
//	private String addressLine1;
//	private String addressLine2;
//	private String addressLine3;
//	@NotBlank
//	private String postalCode;
//	@NotBlank
//	private String city;
//	@NotBlank
//	private String stateOrRegion;
//	@NotBlank
//	private String country;
//	
//	@OneToOne(mappedBy = "address")
//	private BookStoreModel bookstore;
//
//}
