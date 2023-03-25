package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.CarService;
import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarResponse;
import kodlama.io.rentACar.business.rules.CarBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private CarBusinessRules carBusinessRules;
	
	@Override
	public List<GetAllCarsResponse> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> carsResponse = cars.stream()
				.map(car->this.modelMapperService.forResponse()
						.map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
		return carsResponse;
	}


	@Override
	public GetByIdCarResponse getById(int id) {
		Car car = this.carRepository.findById(id).orElseThrow();
		GetByIdCarResponse carResponse = this.modelMapperService.forResponse()
				.map(car, GetByIdCarResponse.class);
		return carResponse;
	}


	@Override
	public void add(CreateCarRequest createCarRequest) {
		this.carBusinessRules.checkIfCarPlateExists(createCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carRepository.save(car);
		
	}


	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		this.carBusinessRules.checkIfCarPlateExists(updateCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest()
				.map(updateCarRequest, Car.class);
		this.carRepository.save(car);
	}


	@Override
	public void delete(int id) {
		this.carRepository.deleteById(id);
		
	}

}
