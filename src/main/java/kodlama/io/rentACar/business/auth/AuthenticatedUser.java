package kodlama.io.rentACar.business.auth;

import java.util.List;

import kodlama.io.rentACar.entities.concretes.Authority;
import kodlama.io.rentACar.entities.concretes.UserPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@ServiceS
public class AuthenticatedUser {

	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private Authority authority;
	private List<UserPermission> userPermissions;
	

}
