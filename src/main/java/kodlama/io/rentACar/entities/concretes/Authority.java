package kodlama.io.rentACar.entities.concretes;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public enum Authority implements GrantedAuthority {
	ADMIN,USER;

	@Override
	public String getAuthority() {
		return name();
	}
	
	
}
