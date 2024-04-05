package san.online.lib.bookstore;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address {
	
	@NotBlank
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	@NotBlank
	private String postalCode;
	@NotBlank
	private String city;
	@NotBlank
	private String stateOrRegion;
	@NotBlank
	private String country;

}
