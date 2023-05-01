package kodlama.io.rentACar.entities.concretes;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "permission_id", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<UserPermission> users = new ArrayList<>();
	
//	@ManyToMany(mappedBy = "permissions")
//	private List<User> users = new ArrayList<>();
	


}
