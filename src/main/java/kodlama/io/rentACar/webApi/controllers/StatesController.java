package kodlama.io.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.abstracts.StateService;
import kodlama.io.rentACar.business.requests.CreateStateRequest;
import kodlama.io.rentACar.business.requests.UpdateStateRequest;
import kodlama.io.rentACar.business.responses.GetAllStatesResponse;
import kodlama.io.rentACar.business.responses.GetByIdStateResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/states")
@AllArgsConstructor
public class StatesController {
	private StateService stateService;
	
	@GetMapping()
	public List<GetAllStatesResponse> getAll(){
		return this.stateService.getAll();
	}
	
	@GetMapping("/{id}")
	public GetByIdStateResponse getById(@PathVariable int id) {
		return this.stateService.getById(id);
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateStateRequest createStateRequest) {
		this.stateService.add(createStateRequest);
	}
	
	@PutMapping()
	public void update(@RequestBody UpdateStateRequest updateStateRequest) {
		this.stateService.update(updateStateRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.stateService.delete(id);
	}
}
