package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarResponse;

public interface CarService {
	List<GetAllCarsResponse> getAll();
	GetByIdCarResponse getById(int id);
	public void add(CreateCarRequest createCarRequest);
	public void update(UpdateCarRequest updateCarRequest);
	public void delete(int id);
}
