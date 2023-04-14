package kodlama.io.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {

	List<UserPermission> findByUserId(int id);
//	@Query("SELECT permissionId FROM UserPermission u WHERE u.userId = :id")
//	List<UserPermission> getByUserId(@Param("id") int id);

	
}
