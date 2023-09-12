package pe.edu.upc.JWTApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.upc.JWTApplication.dtos.AuthResponseDTO;
import pe.edu.upc.JWTApplication.dtos.LoginRequestDTO;
import pe.edu.upc.JWTApplication.dtos.RegisterRequestDTO;
import pe.edu.upc.JWTApplication.services.AuthService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "**")
@RequestMapping("/auth")
public class AuthController {

    // Attributes
    @Autowired
    private final AuthService authService;

    // Methods
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return new ResponseEntity<AuthResponseDTO>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return new ResponseEntity<AuthResponseDTO>(authService.register(request), HttpStatus.OK);
    }

}
