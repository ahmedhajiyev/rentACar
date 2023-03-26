package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.responses.GetByNameBrandResponse;

public interface BrandService {
	List<GetAllBrandsResponse> getAll();
	GetByIdBrandResponse getById(int id);
	GetByNameBrandResponse getByName(String name);
	void add(CreateBrandRequest createBrandRequest);
	void update(UpdateBrandRequest updateBrandRequest);
	void delete(int id);
}
