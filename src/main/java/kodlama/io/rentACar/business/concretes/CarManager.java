package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.CarService;
import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByDailyPriceCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarResponse;
import kodlama.io.rentACar.business.responses.GetByModelYearCarsResponse;
import kodlama.io.rentACar.business.responses.GetByPlateCarResponse;
import kodlama.io.rentACar.business.responses.GetByStateCarsResponse;
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
		car.setPlate(createCarRequest.getPlate().toUpperCase());
		this.carRepository.save(car);
		
	}


	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		this.carBusinessRules.checkIfCarPlateExists(updateCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest()
				.map(updateCarRequest, Car.class);
		car.setPlate(updateCarRequest.getPlate().toUpperCase());
		this.carRepository.save(car);
	}


	@Override
	public void delete(int id) {
		this.carRepository.deleteById(id);
		
	}


	@Override
	public GetByPlateCarResponse getByPlate(String plate) {
		Car car = this.carRepository.findByPlateIgnoreCase(plate);
		GetByPlateCarResponse carResponse = this.modelMapperService.forResponse()
				.map(car, GetByPlateCarResponse.class);
		return carResponse;
	}


	@Override
	public List<GetByModelYearCarsResponse> getByModelYear(int modelYear) {
		List<Car> cars = this.carRepository.findByModelYear(modelYear);
		List<GetByModelYearCarsResponse> carsResponse = cars.stream()
				.map(car->this.modelMapperService.forResponse()
						.map(car, GetByModelYearCarsResponse.class)).collect(Collectors.toList());
		return carsResponse;
	}


	@Override
	public List<GetByDailyPriceCarsResponse> getByDailyPrice(double dailyPrice) {
		List<Car> cars = this.carRepository.findByDailyPrice(dailyPrice);
		List<GetByDailyPriceCarsResponse> carsResponse = cars.stream()
				.map(car->this.modelMapperService.forResponse()
						.map(car, GetByDailyPriceCarsResponse.class)).collect(Collectors.toList());
		return carsResponse;
	}


	@Override
	public List<GetByStateCarsResponse> getByState(int id) {
		List<Car> cars = this.carRepository.findByStateId(id);
		List<GetByStateCarsResponse> carsResponse = cars.stream()
				.map(car->this.modelMapperService.forResponse()		
						.map(car, GetByStateCarsResponse.class)).collect(Collectors.toList());
		return carsResponse;
	}


}


	

