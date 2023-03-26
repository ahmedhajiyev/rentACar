package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByStateCarsResponse {
	private String plate;
	private double dailyPrice;
	private int modelYear;
	private String stateName;
	private String modelName;
	private String brandName;
}
