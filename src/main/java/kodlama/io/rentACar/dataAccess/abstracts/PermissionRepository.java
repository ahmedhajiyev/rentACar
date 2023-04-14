package kodlama.io.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
