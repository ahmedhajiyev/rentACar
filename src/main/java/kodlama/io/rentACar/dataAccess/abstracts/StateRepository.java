package kodlama.io.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	boolean existsByName(String name);
}	
