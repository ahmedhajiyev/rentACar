package kodlama.io.rentACar.webApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.auth.AuthenticationService;
import kodlama.io.rentACar.business.auth.requests.AuthenticationRequest;
import kodlama.io.rentACar.business.auth.requests.RegisterRequest;
import kodlama.io.rentACar.business.auth.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.OK)
	public AuthenticationResponse register(@RequestBody RegisterRequest registerRequest) {
		return this.authenticationService.register(registerRequest);
	}
	
	@PostMapping("/authenticate")
	@ResponseStatus(code = HttpStatus.OK)
	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		return this.authenticationService.authenticate(authenticationRequest);
	}

}
