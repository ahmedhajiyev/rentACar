package kodlama.io.rentACar.business.rules;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.core.utilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.StateRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StateBusinessRules {
	private StateRepository stateRepository;
	
	public void ifExistsCheckStateName(String name) {
		if(this.stateRepository.existsByName(name)) {
			throw new BusinessException("State name already exists");
		}
	}
}
