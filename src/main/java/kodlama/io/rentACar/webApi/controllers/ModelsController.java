package kodlama.io.rentACar.webApi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.auth.AuthenticatedUser;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetByIdModelResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
	private ModelService modelService;
//	private AuthenticatedUser authenticatedUser;
	
	

	@GetMapping()
//	@PreAuthorize("hasAuthority('USER')")
//	@RolesAllowed("ADMIN") // work with roles
//	@Secured("ADMIN") // work with roles
	public List<GetAllModelsResponse> getAll(Principal principal, Authentication auth) throws JsonProcessingException {
//		authenticatedUser.setUsername(auth.getName());
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(principal.getName());
//		System.out.println(json);
//		AuthenticatedUser myobject =objectMapper.readValue(json, AuthenticatedUser.class);

		return modelService.getAll();
	}
	
	@GetMapping("/{id}")
	public GetByIdModelResponse getById(@PathVariable int id) {
		return modelService.getById(id);
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateModelRequest createModelRequest) {
		this.modelService.add(createModelRequest);
	}
	
	@PutMapping()
	public void update(  UpdateModelRequest updateModelRequest) {
		this.modelService.update(updateModelRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.modelService.delete(id);
	}

}
