package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.StateService;
import kodlama.io.rentACar.business.requests.CreateStateRequest;
import kodlama.io.rentACar.business.requests.UpdateStateRequest;
import kodlama.io.rentACar.business.responses.GetAllStatesResponse;
import kodlama.io.rentACar.business.responses.GetByIdStateResponse;
import kodlama.io.rentACar.business.rules.StateBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.StateRepository;
import kodlama.io.rentACar.entities.concretes.State;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StateManager implements StateService{
	private StateRepository stateRepository;
	private ModelMapperService modelMapperService;
	private StateBusinessRules stateBusinessRules;
	
	@Override
	public List<GetAllStatesResponse> getAll() {
		List<State> states = this.stateRepository.findAll();
		List<GetAllStatesResponse> statesResponse = states.stream()
				.map(state->this.modelMapperService.forResponse()
						.map(state, GetAllStatesResponse.class)).collect(Collectors.toList());
		return statesResponse;
	}

	@Override
	public GetByIdStateResponse getById(int id) {
		State state = this.stateRepository.findById(id).orElseThrow();
		GetByIdStateResponse stateResponse = this.modelMapperService.forResponse()
				.map(state, GetByIdStateResponse.class);
		return stateResponse;
	}

	@Override
	public void add(CreateStateRequest createStateRequest) {
		this.stateBusinessRules.ifExistsCheckStateName(createStateRequest.getName());
		State state = this.modelMapperService.forRequest()
				.map(createStateRequest, State.class);
		this.stateRepository.save(state);
		
	}

	@Override
	public void update(UpdateStateRequest updateStateRequest) {
		this.stateBusinessRules.ifExistsCheckStateName(updateStateRequest.getName());
		State state = this.modelMapperService.forRequest()
				.map(updateStateRequest, State.class);
		this.stateRepository.save(state);		
	}

	@Override
	public void delete(int id) {
		this.stateRepository.deleteById(id);		
	}

}
