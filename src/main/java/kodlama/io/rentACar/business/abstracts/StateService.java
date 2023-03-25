package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateStateRequest;
import kodlama.io.rentACar.business.requests.UpdateStateRequest;
import kodlama.io.rentACar.business.responses.GetAllStatesResponse;
import kodlama.io.rentACar.business.responses.GetByIdStateResponse;

public interface StateService {
	List<GetAllStatesResponse> getAll();
	GetByIdStateResponse getById(int id);
	public void add(CreateStateRequest createStateRequest);
	public void update(UpdateStateRequest updateStateRequest);
	public void delete(int id);
}
