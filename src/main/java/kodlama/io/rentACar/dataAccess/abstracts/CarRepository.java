package kodlama.io.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.Car;

public interface CarRepository extends JpaRepository <Car, Integer>{
	boolean existsByPlate(String name);
	Car findByPlateIgnoreCase(String plate);
	List<Car> findByModelYear(int modelYear);
	List<Car> findByDailyPrice(double dailyPrice);
	List<Car> findByStateId(int id);

}
