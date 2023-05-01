package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_permissions")
public class UserPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user_id;
	
	@ManyToOne
	@JoinColumn(name="permission_id")
	private Permission permission_id;
	

//	
//	@ManyToOne
//	@MapsId("userId")
//	private User user;
//	
//	@ManyToOne
//	@MapsId("permissionId")
//	private Permission permission;

	
	
}