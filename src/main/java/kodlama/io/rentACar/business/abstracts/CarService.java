package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByDailyPriceCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarResponse;
import kodlama.io.rentACar.business.responses.GetByModelYearCarsResponse;
import kodlama.io.rentACar.business.responses.GetByPlateCarResponse;
import kodlama.io.rentACar.business.responses.GetByStateCarsResponse;

public interface CarService {
	List<GetAllCarsResponse> getAll();
	GetByIdCarResponse getById(int id);
	GetByPlateCarResponse getByPlate(String plate);
	List<GetByModelYearCarsResponse> getByModelYear(int modelYear);
	List<GetByDailyPriceCarsResponse> getByDailyPrice(double dailyPrice);
	List<GetByStateCarsResponse> getByState(int id);
	public void add(CreateCarRequest createCarRequest);
	public void update(UpdateCarRequest updateCarRequest);
	public void delete(int id);
}
