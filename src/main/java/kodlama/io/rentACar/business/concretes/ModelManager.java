package kodlama.io.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetByIdModelResponse;
import kodlama.io.rentACar.business.rules.ModelBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.dataAccess.abstracts.UserRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import kodlama.io.rentACar.entities.concretes.Permission;
import kodlama.io.rentACar.entities.concretes.User;
import kodlama.io.rentACar.entities.concretes.UserPermission;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	private ModelBusinessRules modelBusinessRules;
	private UserRepository userRepository;

	@Override
	public List<GetAllModelsResponse> getAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		Optional<User> user = userRepository.findByEmail(username);

		List<UserPermission> permissions = user.get().getPermissions();
		ArrayList<String> roles = new ArrayList<String>();

		boolean hasReadPermission = permissions.stream()
				.anyMatch(permission -> "READ".equals(permission.getPermission_id().getName()));
		
		System.out.println(hasReadPermission);
		for (UserPermission permission : permissions) {
			roles.add(permission.getPermission_id().getName());

			if (roles.contains("READ")) {

				List<Model> models = modelRepository.findAll();
				List<GetAllModelsResponse> modelsResponse = models.stream()
						.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
						.collect(Collectors.toList());

				return modelsResponse;
			}
		}
		return null;
	}

	@Override
	public GetByIdModelResponse getById(int id) {
		Model model = modelRepository.findById(id).orElseThrow();
		GetByIdModelResponse modelResponse = this.modelMapperService.forResponse().map(model,
				GetByIdModelResponse.class);
		return modelResponse;
	}

	@Override
	public void add(CreateModelRequest createModelRequest) {
		this.modelBusinessRules.checkIfModelNameExists(createModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(0);
		this.modelRepository.save(model);
	}

	@Override
	public void update(UpdateModelRequest updateModelRequest) {
		this.modelBusinessRules.checkIfModelNameExists(updateModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
		this.modelRepository.save(model);

	}

	@Override
	public void delete(int id) {
		this.modelRepository.deleteById(id);
	}

}
