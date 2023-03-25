package kodlama.io.rentACar.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	@NotNull
	private int id;
	@NotNull
	@NotBlank
	private String plate;
	@NotNull
	@NotBlank
	private int dailyPrice;
	@NotNull
	@NotBlank
	private int modelYear;
	@NotNull
	@NotBlank
	private int stateId;
	@NotNull
	@NotBlank
	private int modelId;
}
