package kodlama.io.rentACar.business.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.auth.requests.AuthenticationRequest;
import kodlama.io.rentACar.business.auth.requests.RegisterRequest;
import kodlama.io.rentACar.business.auth.responses.AuthenticationResponse;
import kodlama.io.rentACar.core.utilities.config.JwtService;
import kodlama.io.rentACar.dataAccess.abstracts.UserRepository;
import kodlama.io.rentACar.entities.concretes.Role;
import kodlama.io.rentACar.entities.concretes.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;


	public AuthenticationResponse register(RegisterRequest registerRequest) {
		var user = User.builder()
									.firstname(registerRequest.getFirstname())
									.lastname(registerRequest.getLastname())
									.email(registerRequest.getEmail())
									.password(passwordEncoder.encode(registerRequest.getPassword()))
									.role(Role.USER)
									.build();
		this.userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		
		var user = this.userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
