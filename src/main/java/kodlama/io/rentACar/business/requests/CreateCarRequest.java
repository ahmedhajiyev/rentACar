package kodlama.io.rentACar.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	@NotNull
	@NotBlank
	private String plate;
	@NotNull
	private int dailyPrice;
	@NotNull
	private int modelYear;
	@NotNull
	private int stateId;
	@NotNull
	private int modelId;
}
