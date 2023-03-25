package kodlama.io.rentACar.business.rules;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.core.utilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarBusinessRules {
	private CarRepository carRepository;
	
	public void checkIfCarPlateExists(String name) {
		if(this.carRepository.existsByPlate(name)) {
			throw new BusinessException("Car plate already exists");
		}
	}
}
